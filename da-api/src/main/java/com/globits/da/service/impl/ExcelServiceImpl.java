package com.globits.da.service.impl;

import com.globits.da.domain.Employee;
import com.globits.da.domain.address.Commune;
import com.globits.da.domain.address.District;
import com.globits.da.domain.address.Province;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.exception.AppException;
import com.globits.da.exception.ErrorCode;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<EmployeeDto> list;

    public ExcelServiceImpl(List<EmployeeDto> list) {
        this.list = list;
    }

    @Override
    public void writerHeaderRow(Sheet sheet){
        Row row = sheet.createRow(0);
        String[] headers = {"STT", "Tên", "Mã", "Email", "Phone", "Age","Province","District","Commune"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }
    @Override
    public void writerDataRows(Sheet sheet){
        int rowNum = 1;
        for (EmployeeDto employee : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum - 1);
            row.createCell(1).setCellValue(employee.getName() != null ? employee.getName() : "N/A");
            row.createCell(2).setCellValue(employee.getCode() != null ? employee.getCode() : "N/A");
            row.createCell(3).setCellValue(employee.getEmail() != null ? employee.getEmail() : "N/A");
            row.createCell(4).setCellValue(employee.getPhone() != null ? employee.getPhone() : "N/A");
            row.createCell(5).setCellValue(employee.getAge() != null ? employee.getAge() : 0);
            row.createCell(6).setCellValue(employee.getProvinceId() != null ? employee.getProvinceId() : 0);
            row.createCell(7).setCellValue(employee.getDistrictId() != null ? employee.getDistrictId() : 0);
            row.createCell(8).setCellValue(employee.getCommuneId() != null ? employee.getCommuneId() : 0);
        }
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        writerHeaderRow(sheet);
        writerDataRows(sheet);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
        }
    }

    public static List<EmployeeDto> excelToEmployees(InputStream is) {
        List<EmployeeDto> list = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);  // Lấy sheet đầu tiên trong file Excel

            int rowNumber = 0;

            for (Row row : sheet) {
                if (rowNumber == 0) { // Bỏ qua hàng đầu tiên (header)
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                EmployeeDto employee = new EmployeeDto();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 1:
                            employee.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            employee.setCode(cell.getStringCellValue());
                            break;
                        case 3:
                            employee.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            employee.setPhone(cell.getStringCellValue());
                            break;
                        case 5:
                            employee.setAge((int) cell.getNumericCellValue());
                            break;
                        case 6:
                            employee.setProvinceId((int) cell.getNumericCellValue());
                            break;
                        case 7:
                            employee.setDistrictId((int) cell.getNumericCellValue());
                            break;
                        case 8:
                            employee.setCommuneId((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                list.add(employee);
                rowNumber++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage());
        }
        return list;
    }

    public static boolean importEmployees(MultipartFile file) {
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

}
