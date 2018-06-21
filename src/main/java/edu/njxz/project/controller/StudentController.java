package edu.njxz.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.njxz.project.model.Course;
import edu.njxz.project.model.Sc;
import edu.njxz.project.model.Student;
import edu.njxz.project.model.UploadStudent;
import edu.njxz.project.service.impl.CourseService;
import edu.njxz.project.service.impl.StudentService;
import edu.njxz.project.service.impl.TeacherService;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("student")
public class StudentController {

    private Logger logger = Logger.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;


    /**
     * @author 周桂星
     * @time 2017/12/27 10:41
     * @description 导入excel表格
     * 
     * @method importEXCEL
     * @param
     * @return void
    **/
    @RequestMapping(value = "importEXCEL", method = RequestMethod.POST)
    public void importEXCEL(HttpServletRequest request) throws IllegalStateException, IOException {

        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        request.setCharacterEncoding("utf-8");
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest mulRequest = resolver.resolveMultipart(request);
        MultipartFile file = mulRequest.getFile("file1");

        String fileName = file.getOriginalFilename();


        String ext = null;

        try {
            ext = fileName.substring(fileName.lastIndexOf("."));
//            ext=file2name.substring(file2name.lastIndexOf("."));
        } catch (Exception e) {
            logger.error("    >>>>>>>ERROR<<<<<<<    ext获取失败！");
        }


        if (".xls".equals(ext)) {
//            workbook = new HSSFWorkbook(new FileInputStream(file2));
            try {
                workbook = new HSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                logger.error("    >>>>>>>ERROR<<<<<<<    导入xls表格出现异常！"+e.getMessage());
            }
        } else if (".xlsx".equals(ext)) {
//            workbook = new XSSFWorkbook(new FileInputStream(file2));
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            workbook = null;
        }


        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();

        sheet = workbook.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        row = sheet.getRow(0);

        int colNum = row.getPhysicalNumberOfCells();

        for (int i = 2; i <= rowNum - 2; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object cellvalue = "";
                Cell cell = row.getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                cellvalue = date;
                            } else {
                                cellvalue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cellvalue = cell.getRichStringCellValue().getString();
                            break;
                        default:
                            cellvalue = "";
                            break;
                    }
                } else {
                    cellvalue = "";
                }
                cellValue.put(j, cellvalue);
                j++;
            }
            content.put(i, cellValue);
        }
        int cid = Integer.parseInt(content.get(2).get(0).toString().substring(4));
        int cnum = Integer.parseInt(content.get(2).get(5).toString().substring(4));
        double credit = Double.parseDouble(content.get(2).get(9).toString().substring(3));
        int period = Integer.parseInt(content.get(2).get(11).toString().substring(3, 5));
        String cname = content.get(2).get(22).toString();
        String cunit = content.get(2).get(44).toString().substring(5);
        String stuamount = content.get(3).get(0).toString().substring(5);
        String tname = content.get(3).get(5).toString().substring(5);
        String timeandplace = content.get(3).get(11).toString().substring(5);
//        int tnum = Integer.parseInt(request.getSession().getAttribute("tinfo").toString());
        int tnum = 111;

        Course course = new Course(cid, cnum, credit, period, cname, cunit, stuamount, tname, timeandplace, tnum);
        List<Integer> stunums = new ArrayList<Integer>();
        for (int i = 6; i < content.size() + 2; i++) {
            Map<Integer, Object> map = content.get(i);
//            int xuhao = Integer.parseInt(map.get(0).toString());
            int xuehao = Integer.parseInt(map.get(1).toString());
            String xueshengxingming = map.get(4).toString();
            String banji = map.get(7).toString();
//            String xingbie = map.get(14).toString();
            try {
                studentService.insert(new Student(xuehao, xueshengxingming, banji));
            } catch (Exception e) {
                logger.error("    >>>>>>>ERROR<<<<<<<    添加学生   "+xuehao+","+xueshengxingming+"    出现异常！");
            }
            stunums.add(xuehao);
        }

        try {
            courseService.insert(course, stunums);
        } catch (Exception e) {
            logger.error("    >>>>>>>ERROR<<<<<<<    添加课程信息 出现异常！");
        }


        request.getSession().removeAttribute("courses");
        request.getSession().setAttribute("courses", teacherService.getCoursekeys(tname));
    }


    /**
     * @author 周桂星
     * @time 2018/3/14 12:16
     * @description 删除名单
    **/
    @RequestMapping(value="deleteEXCEL",method = RequestMethod.POST)
    public void deleteEXCEL(HttpServletRequest request){
        String[] cidnums = request.getParameterValues("selectedCourse");
        for(String cidnum:cidnums){
            int length = cidnum.length();
            int cid = Integer.parseInt(cidnum.substring(0,length-1));
            int cnum= Integer.parseInt(cidnum.substring(length-1));
            try {
                System.out.println(cid+","+cnum);
                courseService.delete(cid,cnum);
            } catch (Exception e) {
                logger.error("    >>>>>>>ERROR<<<<<<<    删除"+cid+""+cnum+"课程出现异常！");
            }
        }




        String tname = request.getSession().getAttribute("tinfo").toString();
        request.getSession().removeAttribute("courses");
        request.getSession().setAttribute("courses", teacherService.getCoursekeys(tname));

    }



    /**
     * @author 周桂星
     * @time 2018/2/14 13:44
     * @description  接收app端上传的考勤数据
    **/
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("---------- 接收到手机上传信息,开始更新考勤信息");

        request.setCharacterEncoding("utf-8");
        InputStreamReader reader = new InputStreamReader(request.getInputStream());
        BufferedReader buffer = new BufferedReader(reader);
        String data = buffer.readLine();


        JSONObject jsonObject = JSONObject.parseObject(data);

        int cid = jsonObject.getIntValue("cid");
        int cnum = jsonObject.getIntValue("cnum");
        int classnum = jsonObject.getIntValue("classnum");

        JSONArray jsonArray = jsonObject.getJSONArray("students");
        int s = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject student = jsonArray.getJSONObject(i);
            if (student.getIntValue("status") == 1) {
                s++;
                int stunum = student.getIntValue("stunum");
                studentService.upload(new UploadStudent(stunum,cid,cnum,classnum));
            }else {
                s++;
                int stunum = student.getIntValue("stunum");
                studentService.uploadXX(new UploadStudent(stunum,cid,cnum,classnum));
            }
        }
        response.getWriter().append("" + s + "");
    }

    
    
    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:45
     * @description   增加学生
    **/
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void insert(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        int stunum = Integer.parseInt(request.getParameter("stunum"));
        String stuname = request.getParameter("stuname");

        int cid = Integer.parseInt(request.getSession().getAttribute("cid").toString());
        int cnum = Integer.parseInt(request.getSession().getAttribute("cnum").toString());
        studentService.insert(new Student(stunum, stuname, ""),cid,cnum);
    }

    /**
     * @author 周桂星
     * @time 2018/2/14 13:45
     * @description 查询所有学生信息
    **/
    @RequestMapping(value = "find/all", method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        List<Student> students = studentService.findAll();
        response.setContentType("application/json");
        response.getWriter().append(JSON.toJSONString(students));
    }





    /**
     * @author 周桂星
     * @time 2018/2/17 21:38
     * @description 下载名单
    **/
    @RequestMapping(value = "find/allSc", method = RequestMethod.POST)
    public void findAllSc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String tname = request.getParameter("tname");
        List<Sc> students = studentService.findAllSc(tname);
        response.setContentType("application/json");
        response.getWriter().append(JSON.toJSONString(students));
    }
    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:45
     * @description 查询某班级学生信息
    **/
    @RequestMapping(value = "findbyclass", method = RequestMethod.GET)
    public void findByClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        int cid = Integer.parseInt(request.getParameter("cid").toString());
        int cnum = Integer.parseInt(request.getParameter("cnum").toString());
        request.getSession().setAttribute("cid", cid);
        request.getSession().setAttribute("cnum", cnum);
        List<Student> students = studentService.findByClass(cid, cnum);
        response.setContentType("application/json");
        response.getWriter().append(JSON.toJSONString(students));
    }

    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:46
     * @description 切换班级
    **/
    @RequestMapping(value = "tiaozhuan", method = RequestMethod.GET)
    public ModelAndView tiaozhuan(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        ModelAndView modelAndView = new ModelAndView("studentList");
        int cid = Integer.parseInt(request.getParameter("cid").toString());
        int cnum = Integer.parseInt(request.getParameter("cnum").toString());
        String cname = request.getParameter("cname").toString();

        request.getSession().setAttribute("cname",cname);

        modelAndView.addObject("cid", cid);
        modelAndView.addObject("cnum", cnum);
        modelAndView.addObject("cname", cname+"班");
        return modelAndView;
    }

    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:46
     * @description 删除学生信息
    **/
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int stunum = Integer.parseInt(request.getParameter("stunum"));
        studentService.delete(stunum);
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json");
        response.getWriter().append("1");
    }

    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:52
     * @description 修改学生信息
    **/
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@ModelAttribute Student student, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        int cid = Integer.parseInt(request.getSession().getAttribute("cid").toString());
        int cnum = Integer.parseInt(request.getSession().getAttribute("cnum").toString());
        student.setCid(cid);
        student.setCnum(cnum);
        studentService.update(student);
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json");
        response.getWriter().append("1");
    }

    
    /**
     * @author 周桂星
     * @time 2018/2/14 13:53
     * @description 导出excel表格
    **/
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportExl(HttpServletResponse response,HttpServletRequest request) throws IOException, WriteException {
        response.setContentType("application/msexcel");

        String cname = request.getSession().getAttribute("cname").toString();

        int cnum = Integer.parseInt(request.getSession().getAttribute("cnum").toString());
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String(cname.getBytes("GB2312"), "ISO-8859-1") + ".xls");
        response.setHeader("pragma", "no-cache");
        ServletOutputStream sos = response.getOutputStream();
        WritableWorkbook wwb = jxl.Workbook.createWorkbook(sos);
        WritableSheet ws = wwb.createSheet("考勤表", 0);
        ws.mergeCells(0, 0, 4, 0);
        ws.addCell(new Label(0, 0, "考勤表"));
        String[] column1 = {"学号", "姓名", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18", "时间"};
        for (int i = 0; i < column1.length; i++) {
            ws.addCell(new Label(i, 1, column1[i]));
        }
        int cid = Integer.parseInt(request.getSession().getAttribute("cid").toString());
        List<Student> students = studentService.findByClass(cid,cnum);
        for (int i = 0; i < students.size(); i++) {
            ws.addCell(new Label(0, i + 2, String.valueOf(students.get(i).getStunum())));
            ws.addCell(new Label(1, i + 2, students.get(i).getStuname()));
            ws.addCell(new Label(2, i + 2, String.valueOf(students.get(i).getClass1())));
            ws.addCell(new Label(3, i + 2, String.valueOf(students.get(i).getClass2())));
            ws.addCell(new Label(4, i + 2, String.valueOf(students.get(i).getClass3())));
            ws.addCell(new Label(5, i + 2, String.valueOf(students.get(i).getClass4())));
            ws.addCell(new Label(6, i + 2, String.valueOf(students.get(i).getClass5())));
            ws.addCell(new Label(7, i + 2, String.valueOf(students.get(i).getClass6())));
            ws.addCell(new Label(8, i + 2, String.valueOf(students.get(i).getClass7())));
            ws.addCell(new Label(9, i + 2, String.valueOf(students.get(i).getClass8())));
            ws.addCell(new Label(10, i + 2, String.valueOf(students.get(i).getClass9())));
            ws.addCell(new Label(11, i + 2, String.valueOf(students.get(i).getClass10())));
            ws.addCell(new Label(12, i + 2, String.valueOf(students.get(i).getClass11())));
            ws.addCell(new Label(13, i + 2, String.valueOf(students.get(i).getClass12())));
            ws.addCell(new Label(14, i + 2, String.valueOf(students.get(i).getClass13())));
            ws.addCell(new Label(15, i + 2, String.valueOf(students.get(i).getClass14())));
            ws.addCell(new Label(16, i + 2, String.valueOf(students.get(i).getClass15())));
            ws.addCell(new Label(17, i + 2, String.valueOf(students.get(i).getClass16())));
            ws.addCell(new Label(18, i + 2, String.valueOf(students.get(i).getClass17())));
            ws.addCell(new Label(19, i + 2, String.valueOf(students.get(i).getClass18())));
        }
        wwb.write();
        wwb.close();
    }



    @RequestMapping(value = "total",method=RequestMethod.POST)
    public void getTotal(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        int cnum = Integer.parseInt(request.getParameter("cnum"));

        List<Map> list = studentService.getTotal(cid,cnum);

        String send="";
        for (Map map : list) {
                send += map.get("stuname") + ":" + map.get("total") + "\n";
        }


        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(send);

    }
}
