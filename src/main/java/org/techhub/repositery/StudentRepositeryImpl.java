package org.techhub.repositery;

import java.util.ArrayList;
import java.util.List;

import org.techhub.bdconfig.DBInitialize;
import org.techhub.model.StudentModel;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

public class StudentRepositeryImpl extends DBInitialize implements StudentRepositery {

    @Override
    public boolean idAddStudent(StudentModel model) {
        try {
            stmt = conn.prepareStatement("INSERT INTO student (name, email, department, contact) VALUES (?,?,?,?)");
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getEmail());
            stmt.setString(3, model.getDept());
            stmt.setInt(4, model.getContact()); // new contact field
            int value = stmt.executeUpdate();
            return value > 0 ? true : false;
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return false;
    }

    @Override
    public List<StudentModel> getAllStudent() {
        List<StudentModel> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM student");
            rs = stmt.executeQuery();
            while (rs.next()) {
                StudentModel model = new StudentModel();
                model.setStudentId(rs.getInt("studentid"));
                model.setName(rs.getString("name"));
                model.setEmail(rs.getString("email"));
                model.setDept(rs.getString("department"));
                model.setContact(rs.getInt("contact")); // fetch contact
                list.add(model);
            }
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return list;
    }

    @Override
    public boolean isDeleteStudent(int id) {
        try {
            stmt = conn.prepareStatement("DELETE FROM student WHERE studentid=?");
            stmt.setInt(1, id);
            int value = stmt.executeUpdate();
            return value > 0 ? true : false;
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return false;
    }

    @Override
    public boolean isUpdateStudent(StudentModel model) {
        try {
            stmt = conn.prepareStatement(
                    "UPDATE student SET name=?, email=?, department=?, contact=? WHERE studentid=?");
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getEmail());
            stmt.setString(3, model.getDept());
            stmt.setInt(4, model.getContact()); // update contact
            stmt.setInt(5, model.getStudentId());
            int value = stmt.executeUpdate();
            return value > 0 ? true : false;
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return false;
    }

    @Override
    public List<StudentModel> getStudentsByDepartment(String department) {
        List<StudentModel> sList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM student WHERE department=?");
            stmt.setString(1, department);
            rs = stmt.executeQuery();

            while (rs.next()) {
                StudentModel m = new StudentModel();
                m.setStudentId(rs.getInt("studentid"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setDept(rs.getString("department"));
                m.setContact(rs.getInt("contact")); // contact field
                sList.add(m);
            }
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return sList;
    }

    @Override
    public List<StudentModel> getStudentsByEmail(String email) {
        List<StudentModel> SEList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM student WHERE email=?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while (rs.next()) {
                StudentModel m = new StudentModel();
                m.setStudentId(rs.getInt("studentid"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setDept(rs.getString("department"));
                m.setContact(rs.getInt("contact")); // contact field
                SEList.add(m);
            }
        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
        return SEList;
    }

    @Override
    public void geStudentReport(List<StudentModel> student) {
        if (student == null || student.isEmpty()) {
            System.out.println("No students available to generate PDF.");
            return;
        }

        String pdfPath = "StudentReport.pdf";

        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Student Report").setBold().setFontSize(18).setMarginBottom(20));

            Table table = new Table(new float[] { 1, 4, 5, 3, 3 }); 
            table.setWidth(100);

            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Email")));
            table.addHeaderCell(new Cell().add(new Paragraph("Department")));
            table.addHeaderCell(new Cell().add(new Paragraph("Contact"))); // header

            for (StudentModel s : student) {
                table.addCell(String.valueOf(s.getStudentId()));
                table.addCell(s.getName());
                table.addCell(s.getEmail());
                table.addCell(s.getDept());
                table.addCell(String.valueOf(s.getContact())); // data
            }

            document.add(table);
            document.close();

            System.out.println("======================================");
            System.out.println("✅ Student PDF Download Successful!");
            System.out.println("File saved at: " + pdfPath);
            System.out.println("======================================");

        } catch (Exception ex) {
            System.out.println("Error is " + ex);
        }
    }
}
