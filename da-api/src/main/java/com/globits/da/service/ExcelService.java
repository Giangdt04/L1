package com.globits.da.service;
import com.globits.da.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ExcelService {
    void writerHeaderRow(Sheet sheet);

    void writerDataRows(Sheet sheet);

    void exportToExcel(HttpServletResponse response) throws IOException;

}
