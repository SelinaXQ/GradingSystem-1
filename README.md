# GradingSystem

*列出每个页面需要用到的函数和共享class(需要传参到下一个窗口的数据

1. Login

class（传参 + 当前页面）: 

Semester # 当前学期

method:

public boolean login(String userName, String pwd)
return false表示用户名密码不对，return true表示登陆成功

2. Courses

class（传参）: 

Course # 用户点击的课程

class（当前页面）：
ArrayList<Course> courses # 这学期的所有课程

method:
public Course getCourseInfo(String cID)
public ArrayList<Course> getCoursesBySemester(String semester)
return 当前学期的所有课程

3. HistoryCourses

class（传参）: 
Course # 用户点击的课程(Overview)
Semester # 当前学期 （Back到上一个）

class（当前页面）：
ArrayList<Semester> sems;  # 所有学期
ArrayList<Course> courses # 某学期的所有课程

method:
public ArrayList<Semester> getSemesters()  # 所有学期
public ArrayList<Course> getCoursesBySemester(String semester)
return 所选学期的所有课程

4. Edit/add course criteria
Save操作指保存整个页面上的结果，但不能处理Delete

class（当前页面）：

Course 
ArrayList<GeneralCriteria>
ArrayList<DetailedCriteria> 
(每个GeneralCriteria 对应一个ArrayList<DetailedCriteria>）
method:

public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) 
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)
return false表示加起来的百分比不等于1
public void deleteGeneralCriteria(GeneralCriteria gCriteria, boolean ifTemplate) 

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris) 

5. Your Template

class（传参）: 
Course # 用户点击的课程

class（当前页面）：
ArrayList<Semester> sems;  # 所有学期
ArrayList<Course> courses # 某学期的所有课程

method:
public ArrayList<Semester> getSemesters()  # 所有学期
public ArrayList<Course> getCoursesBySemester(String semester)
return 所选学期的所有课程

6. Edit Criteria Type（那个Delete按钮是没有的
这页只进行UI操作，在页面4同一增删改查就好了

7. Go to Course
（这个页面应该有两个Save, 一个Save评分标准，一个Save打分）

class（当前页面）：

Course 
ArrayList<GeneralCriteria>
ArrayList<DetailedCriteria> 
(每个GeneralCriteria 对应一个ArrayList<DetailedCriteria>）
ArrayList<GiveDetailedGrades>

method:

public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) 
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)
return false表示加起来的百分比不等于1

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course, DetailedCriteria dCriteria）
返回直接对应表格每一列的ArrayList，每一项表示一个学生

public void updateStudentsDetailedGrade(DetailedCriteria dCriteria, ArrayList<GiveDetailedGrades> dGs) 
对应表格每一列的ArrayList，每一项表示一个学生

8. StudentManagement

class（当前页面）：
ArrayList<StudentInfo>
Course 

method:
public ArrayList<StudentInfo> getStudentsByCourseID(Course course) 
public void saveOpUpdateStudentsInfo(ArrayList<StudentInfo> sInfos, Course c)

9. OverView




