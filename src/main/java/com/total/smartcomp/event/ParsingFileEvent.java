package com.total.smartcomp.event;

import com.total.smartcomp.entity.Employee;
import com.total.smartcomp.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ParsingFileEvent {

    @Value("${input.datas.filePath}")
    private Resource resource;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ParsingFileEvent(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public List<Employee> createEmployeeDatasFromFile() {
        final long nbRecords = employeeRepository.count();
        //If no records found in the database we initialize the tables with datas from file
        if (nbRecords < 1) {
            try {
                //Datas extraction from resource file
                final InputStream inputstream = resource.getInputStream();
                final XSSFWorkbook wb = new XSSFWorkbook(inputstream);
                final XSSFSheet baseSheet = wb.getSheet("Base");

                //Treatement of the "Base" sheet
                final List<Employee> employeeList = new ArrayList<>();
                final FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                final int firstRowNum = getFirstNoEmptyRow(baseSheet).getRowNum(); //First filled row index
                int columnIndex = getNextDataSetColIndex(baseSheet, firstRowNum, 0); //Each column represent one employee datasets
                while (columnIndex != -1) {
                    final Optional<String> GGI = getStringCellValue(evaluator, baseSheet.getRow(1).getCell(columnIndex));
                    final Optional<String> name = getStringCellValue(evaluator, baseSheet.getRow(2).getCell(columnIndex));
                    final Optional<Double> hiringTs = getNumericCellValue(evaluator, baseSheet.getRow(3).getCell(columnIndex));
                    String firstName = "", lastName = "";
                    if (!name.isEmpty() && name.isPresent()) {
                        final String[] separedName = name.get().split("(?=\\s[A-ZÀ-ÖØ-Ý][a-zà-öø-ý])", 2);
                        if (separedName.length > 0) {
                            lastName = separedName[0];
                            if (separedName.length > 1) {
                                firstName = separedName[1];
                            }
                        }
                    }

                    final Employee employee = new Employee(GGI.get(), firstName, lastName, null);
                    employeeList.add(employee);

                    //Get net dataset column Index
                    columnIndex = getNextDataSetColIndex(baseSheet, firstRowNum, columnIndex);
                }
                //Save employee List
                employeeRepository.saveAll(employeeList);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private Optional<String> getStringCellValue(final FormulaEvaluator evaluator, final XSSFCell cell) {
        return Optional.ofNullable(evaluator.evaluate(cell).getStringValue());
    }

    private Optional<Double> getNumericCellValue(final FormulaEvaluator evaluator, final XSSFCell cell) {
        return Optional.ofNullable(evaluator.evaluate(cell).getNumberValue());
    }

    private XSSFRow getFirstNoEmptyRow(final XSSFSheet sheet) {
        int currentIndex = 0;
        XSSFRow row = sheet.getRow(currentIndex++);
        while (row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
            row = sheet.getRow(currentIndex++);
        }
        return row;
    }

    private boolean isCellDefine(final XSSFSheet sheet, final int rowNum, final int columnNum) {
        return sheet.getRow(rowNum).getCell(columnNum) != null;
    }

    private boolean isBlankCell(final XSSFSheet sheet, final int rowNum, final int columnNum) {
        return sheet.getRow(rowNum).getCell(columnNum) != null
                && sheet.getRow(rowNum).getCell(columnNum).getCellType() == CellType.BLANK;
    }

    private int getNextDataSetColIndex(final XSSFSheet sheet, final int rowNum, final int columnNum) {
        int currentIndex = columnNum;
        do {
            currentIndex++;
            if (!isCellDefine(sheet, rowNum, currentIndex)) { //If we get a No define cell, it means that we are at the end of the dataset array
                currentIndex = -1;
                break;
            }
        } while (isBlankCell(sheet, rowNum, currentIndex));
        return currentIndex;
    }

}
