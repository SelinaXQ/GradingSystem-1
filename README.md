# GradingSystem

0. 可能会遇到的问题
（1）一大堆文件画红×了: 
a. 每一个FX class run的时候都需要配置VM argument，在run configuration里，很可能pull一次后，你的配置不见了，需要重新配置。
b. Referenced Library里所有的东西remove掉，从lib包里全选全部，右键，configure path，add to path（IntellJ里可能不是这样的，但原理类似）
c. Java Fx 的User Library，可能也需要重新手动添加。
（2）数据库连不上
是不是忘记改用户名和密码了。。。
（3）为了避免问题（1）
commit的时候不要把自己的classpath，gignore，project这三个文件交上去
（4）pull冲突了
a. 把github里classpath，gignore，project这三个文件删了试试
b. 看看是哪个文件冲突，把自己的那个文件删掉或备份，再pull，再提交自己的修改。
c. 很遗憾，可能要整个重新从github import了。
（5）***注意事项！注意事项！当你要save一个东西，例如Criteria们，构造ArrayList的时候，<Criteria>类的第一个属性，也就是主键，也就是那个我们用哈希值（uuid）构造出来的东西，例如GCriID属性和DCriID属性，这个属性，实例化的时候要传null！不然会Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) :

1. 文件结构
-src
-application: UI class & fxml
-db: Operation.java 可以在UI中直接调用的函数，封装了所有db文件夹下其他文件中的方法，可以实现相应功能（比如要加载一个学期所有的课程的ArrayList，要保存一个excel表里学生的信息）
-pojo: 对应于database中每张表的class
-uitable: 对应于UI中每张表的class
-hibernate.cfg.xml 需要在这里改动数据库的username和password（root&123312）
-defalut package: 用来测试数据库接口的，不用管它

2. A basic Idea: 
在UI中，你需要实例化一些对象，通过管理这些对象来操作UI上的表(table，List).
比如UI中有一个table，那么这个表对应于UITable包中的class，表的每一行是一个class，整张表是一个ArrayList
再比如对于一个Course的信息，需要贯穿于每个页面，那么一个Course的object就会作为界面跳转时传参的一个参数，从而使这个object跟到每一个界面，这样在任何一个界面上都能读取这个Course下的信息
所以，当在UI中调用Operation.java 中的函数时，它的参数通常是一个object或者ArrayList（就是整张表的信息），它的返回值可能为void（save到数据库时），或者一个ArrayList（查数据库后，填充信息到UI中的一张表）。
所以，当在UI.java上想要做增删改查database，只需要实例化一个Operation，然后operation.xxxxxx(下面列出的函数)，就可以了

3. 每个页面需要用到的函数和class
(class分为了需要传参的class和当前页面的class，可能不全，按需添加
(一般来说把列出的所有class作为UI.java文件的属性

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
public Course getCourseInfo(String cID) # 获取一个课程的obj
public ArrayList<Course> getCoursesBySemester(String semester) # 获取当前学期的所有课程

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
Save按钮，指保存整个页面上的结果，包括了Add和update，但不能处理Delete
如果要Delete，请选择UI Table中的一行，按Delete按钮

class（当前页面）：

Course 
ArrayList<GeneralCriteria>
ArrayList<ArrayList<DetailedCriteria> >
(每个GeneralCriteria 对应一个ArrayList<DetailedCriteria>，所以其实这里需要多个ArrayList<DetailedCriteria>，即ArrayList<ArrayList<DetailedCriteria> >，它的index和ArrayList<GeneralCriteria>的index是对应的）

method:
public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate)   # 通过CourseID得到ArrayList<GeneralCriteria>，boolean=true表示是在template界面调用这个函数，也就是打开一个模板，boolean=false表示是在Edit/add course criteria界面调用这个函数，也就是打开这个课程的general criterias
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)  # 保存ArrayList<GeneralCriteria> gCris的信息，return false表示加起来的百分比不等于1
public void deleteGeneralCriteria(GeneralCriteria gCriteria, boolean ifTemplate)  # 删除一项General Criteria

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)
与 上 同 理。

public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris)  # 在Template界面调用，用于保存一个课程的Template，其实是自动调用了boolean = true情况下的SaveDetail&GeneralCriteria函数


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
这页只进行UI操作，在页面4增删改查数据库

7. Grading
（这个页面应该有两个Save, 一个Save评分标准，一个Save打分）
关于函数的描述请见界面4

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

public ArrayList<DetailedCriteria> getDetailedCriteriasByGenerCriID(String gCriID, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course, DetailedCriteria dCriteria）
# 返回直接对应表格每一列的ArrayList，每一项表示一个学生

public void updateStudentsDetailedGrade(DetailedCriteria dCriteria, ArrayList<GiveDetailedGrades> dGs) 
# ArrayList<GiveDetailedGrades> dGs的每一项表示一个学生的grade

8. StudentManagement

class（当前页面）：
ArrayList<StudentInfo>
Course 

method:
public ArrayList<StudentInfo> getStudentsByCourseID(Course course) 
public void saveOpUpdateStudentsInfo(ArrayList<StudentInfo> sInfos, Course c)

9. OverView

class (当前页面）：
ArrayList<Overview>

method:
public ArrayList<Overview> getOverviewInfoByCourseID(Course course) 

Yep，只有这一个函数和一个ArrayList obj，包括总分计算也是已经封装好的，但在UI java里拆包这ArrayList<Overview>还挺麻烦的

这个Overview类的属性如下：
private Student student; // 对应了UI Table的前四列
private ArrayList<HashMap<String, DetailedGrade>> dcs;  // the String is the general Score 's id (primary key)
private ArrayList<GeneralGrade> gcScores;
private double total; // composite
private String grade;  // A+, A, ...

GeneralGrade class:
private String gCriID;  // *, General Criteria ID, [HS], created by courseID(cID) and genenal criteria type(GenCriType)	
private double score;
private double per;

DetailedGrade class:
private String dCriID;  // Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and detailed criteria type(deCriType)
private double score;
private double per;

这个Overview类是从表格可以expand的角度设计的，可能需要查阅一下如何在FX中动态设置Table的列数。
当表格没有expand的时候，呈现出来的是每一个general criteria的分数（百分比），此时这几列是调用ArrayList<GeneralGrade> gcScores，size()就是相应的列数。
GeneralGrade类包括了General Cri ID，percentage，score，最后只要getScore就可以了。percentage属性是封装的方法里计算总分用的，不用在意。
当表格expand的时候，呈现出来的是上面的每一个general criteria的分数(ArrayList<GeneralGrade> gcScores)，以及这一个general criteria（eg.Quiz）下的detailed criteria(eg.quiz1 2 3)。当然，也可以实现成只呈现后者，把这一个general criteria（eg.Quiz）的总分替换掉。
对于一个general criteria（eg.Quiz），为了得到detailed criteria(eg.quiz1 2 3)的成绩，需要访问属性ArrayList<HashMap<String, DetailedGrade>> dcs。
ArrayList<HashMap<String, DetailedGrade>> dcs的每一项，是一个HashMap<String, DetailedGrade>，String指对应的general criteria ID。
所以为了拿到特定一个general criteria的HashMap<String, DetailedGrade>，需要对ArrayList<GeneralGrade>中的每一个GeneralGrade，for循环遍历ArrayList<HashMap<String, DetailedGrade>> ，当HashMap<String, DetailedGrade>的key值 = GeneralGrade class中的gCriID属性时，就是这时候要的HashMap了，取出DetailedGrade，getScore，就得到表格中的分数。
（可以查查怎么查询和getHashMap的值）
嗯，希望这个描述足够清楚了…







