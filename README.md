# GradingSystem

0. ���ܻ�����������
��1��һ����ļ��������: 
a. ÿһ��FX class run��ʱ����Ҫ����VM argument����run configuration��ܿ���pullһ�κ�������ò����ˣ���Ҫ�������á�
b. Referenced Library�����еĶ���remove������lib����ȫѡȫ�����Ҽ���configure path��add to path��IntellJ����ܲ��������ģ���ԭ�����ƣ�
c. Java Fx ��User Library������Ҳ��Ҫ�����ֶ���ӡ�
��2�����ݿ�������
�ǲ������Ǹ��û����������ˡ�����
��3��Ϊ�˱������⣨1��
commit��ʱ��Ҫ���Լ���classpath��gignore��project�������ļ�����ȥ
��4��pull��ͻ��
a. ��github��classpath��gignore��project�������ļ�ɾ������
b. �������ĸ��ļ���ͻ�����Լ����Ǹ��ļ�ɾ���򱸷ݣ���pull�����ύ�Լ����޸ġ�
c. ���ź�������Ҫ�������´�github import�ˡ�
��5��***ע�����ע���������Ҫsaveһ������������Criteria�ǣ�����ArrayList��ʱ��<Criteria>��ĵ�һ�����ԣ�Ҳ����������Ҳ�����Ǹ������ù�ϣֵ��uuid����������Ķ���������GCriID���Ժ�DCriID���ԣ�������ԣ�ʵ������ʱ��Ҫ��null����Ȼ��Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) :

1. �ļ��ṹ
-src
-application: UI class & fxml
-db: Operation.java ������UI��ֱ�ӵ��õĺ�������װ������db�ļ����������ļ��еķ���������ʵ����Ӧ���ܣ�����Ҫ����һ��ѧ�����еĿγ̵�ArrayList��Ҫ����һ��excel����ѧ������Ϣ��
-pojo: ��Ӧ��database��ÿ�ű��class
-uitable: ��Ӧ��UI��ÿ�ű��class
-hibernate.cfg.xml ��Ҫ������Ķ����ݿ��username��password��root&123312��
-defalut package: �����������ݿ�ӿڵģ����ù���

2. A basic Idea: 
��UI�У�����Ҫʵ����һЩ����ͨ��������Щ����������UI�ϵı�(table��List).
����UI����һ��table����ô������Ӧ��UITable���е�class�����ÿһ����һ��class�����ű���һ��ArrayList
�ٱ������һ��Course����Ϣ����Ҫ�ᴩ��ÿ��ҳ�棬��ôһ��Course��object�ͻ���Ϊ������תʱ���ε�һ���������Ӷ�ʹ���object����ÿһ�����棬�������κ�һ�������϶��ܶ�ȡ���Course�µ���Ϣ
���ԣ�����UI�е���Operation.java �еĺ���ʱ�����Ĳ���ͨ����һ��object����ArrayList���������ű����Ϣ�������ķ���ֵ����Ϊvoid��save�����ݿ�ʱ��������һ��ArrayList�������ݿ�������Ϣ��UI�е�һ�ű���
���ԣ�����UI.java����Ҫ����ɾ�Ĳ�database��ֻ��Ҫʵ����һ��Operation��Ȼ��operation.xxxxxx(�����г��ĺ���)���Ϳ�����

3. ÿ��ҳ����Ҫ�õ��ĺ�����class
(class��Ϊ����Ҫ���ε�class�͵�ǰҳ���class�����ܲ�ȫ���������
(һ����˵���г�������class��ΪUI.java�ļ�������

1. Login

class������ + ��ǰҳ�棩: 

Semester # ��ǰѧ��

method:

public boolean login(String userName, String pwd)
return false��ʾ�û������벻�ԣ�return true��ʾ��½�ɹ�

2. Courses

class�����Σ�: 

Course # �û�����Ŀγ�

class����ǰҳ�棩��
ArrayList<Course> courses # ��ѧ�ڵ����пγ�

method:
public Course getCourseInfo(String cID) # ��ȡһ���γ̵�obj
public ArrayList<Course> getCoursesBySemester(String semester) # ��ȡ��ǰѧ�ڵ����пγ�

3. HistoryCourses

class�����Σ�: 
Course # �û�����Ŀγ�(Overview)
Semester # ��ǰѧ�� ��Back����һ����

class����ǰҳ�棩��
ArrayList<Semester> sems;  # ����ѧ��
ArrayList<Course> courses # ĳѧ�ڵ����пγ�

method:
public ArrayList<Semester> getSemesters()  # ����ѧ��
public ArrayList<Course> getCoursesBySemester(String semester)
return ��ѡѧ�ڵ����пγ�

4. Edit/add course criteria
Save��ť��ָ��������ҳ���ϵĽ����������Add��update�������ܴ���Delete
���ҪDelete����ѡ��UI Table�е�һ�У���Delete��ť

class����ǰҳ�棩��

Course 
ArrayList<GeneralCriteria>
ArrayList<ArrayList<DetailedCriteria> >
(ÿ��GeneralCriteria ��Ӧһ��ArrayList<DetailedCriteria>��������ʵ������Ҫ���ArrayList<DetailedCriteria>����ArrayList<ArrayList<DetailedCriteria> >������index��ArrayList<GeneralCriteria>��index�Ƕ�Ӧ�ģ�

method:
public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate)   # ͨ��CourseID�õ�ArrayList<GeneralCriteria>��boolean=true��ʾ����template����������������Ҳ���Ǵ�һ��ģ�壬boolean=false��ʾ����Edit/add course criteria����������������Ҳ���Ǵ�����γ̵�general criterias
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)  # ����ArrayList<GeneralCriteria> gCris����Ϣ��return false��ʾ�������İٷֱȲ�����1
public void deleteGeneralCriteria(GeneralCriteria gCriteria, boolean ifTemplate)  # ɾ��һ��General Criteria

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)
�� �� ͬ ��

public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris)  # ��Template������ã����ڱ���һ���γ̵�Template����ʵ���Զ�������boolean = true����µ�SaveDetail&GeneralCriteria����


5. Your Template

class�����Σ�: 
Course # �û�����Ŀγ�

class����ǰҳ�棩��
ArrayList<Semester> sems;  # ����ѧ��
ArrayList<Course> courses # ĳѧ�ڵ����пγ�

method:
public ArrayList<Semester> getSemesters()  # ����ѧ��
public ArrayList<Course> getCoursesBySemester(String semester)
return ��ѡѧ�ڵ����пγ�

6. Edit Criteria Type���Ǹ�Delete��ť��û�е�
��ҳֻ����UI��������ҳ��4��ɾ�Ĳ����ݿ�

7. Grading
�����ҳ��Ӧ��������Save, һ��Save���ֱ�׼��һ��Save��֣�
���ں����������������4

class����ǰҳ�棩��

Course 
ArrayList<GeneralCriteria>
ArrayList<DetailedCriteria> 
(ÿ��GeneralCriteria ��Ӧһ��ArrayList<DetailedCriteria>��
ArrayList<GiveDetailedGrades>

method:

public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) 
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)
return false��ʾ�������İٷֱȲ�����1

public ArrayList<DetailedCriteria> getDetailedCriteriasByGenerCriID(String gCriID, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course, DetailedCriteria dCriteria��
# ����ֱ�Ӷ�Ӧ���ÿһ�е�ArrayList��ÿһ���ʾһ��ѧ��

public void updateStudentsDetailedGrade(DetailedCriteria dCriteria, ArrayList<GiveDetailedGrades> dGs) 
# ArrayList<GiveDetailedGrades> dGs��ÿһ���ʾһ��ѧ����grade

8. StudentManagement

class����ǰҳ�棩��
ArrayList<StudentInfo>
Course 

method:
public ArrayList<StudentInfo> getStudentsByCourseID(Course course) 
public void saveOpUpdateStudentsInfo(ArrayList<StudentInfo> sInfos, Course c)

9. OverView

class (��ǰҳ�棩��
ArrayList<Overview>

method:
public ArrayList<Overview> getOverviewInfoByCourseID(Course course) 

Yep��ֻ����һ��������һ��ArrayList obj�������ּܷ���Ҳ���Ѿ���װ�õģ�����UI java������ArrayList<Overview>��ͦ�鷳��

���Overview����������£�
private Student student; // ��Ӧ��UI Table��ǰ����
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

���Overview���Ǵӱ�����expand�ĽǶ���Ƶģ�������Ҫ����һ�������FX�ж�̬����Table��������
�����û��expand��ʱ�򣬳��ֳ�������ÿһ��general criteria�ķ������ٷֱȣ�����ʱ�⼸���ǵ���ArrayList<GeneralGrade> gcScores��size()������Ӧ��������
GeneralGrade�������General Cri ID��percentage��score�����ֻҪgetScore�Ϳ����ˡ�percentage�����Ƿ�װ�ķ���������ܷ��õģ��������⡣
�����expand��ʱ�򣬳��ֳ������������ÿһ��general criteria�ķ���(ArrayList<GeneralGrade> gcScores)���Լ���һ��general criteria��eg.Quiz���µ�detailed criteria(eg.quiz1 2 3)����Ȼ��Ҳ����ʵ�ֳ�ֻ���ֺ��ߣ�����һ��general criteria��eg.Quiz�����ܷ��滻����
����һ��general criteria��eg.Quiz����Ϊ�˵õ�detailed criteria(eg.quiz1 2 3)�ĳɼ�����Ҫ��������ArrayList<HashMap<String, DetailedGrade>> dcs��
ArrayList<HashMap<String, DetailedGrade>> dcs��ÿһ���һ��HashMap<String, DetailedGrade>��Stringָ��Ӧ��general criteria ID��
����Ϊ���õ��ض�һ��general criteria��HashMap<String, DetailedGrade>����Ҫ��ArrayList<GeneralGrade>�е�ÿһ��GeneralGrade��forѭ������ArrayList<HashMap<String, DetailedGrade>> ����HashMap<String, DetailedGrade>��keyֵ = GeneralGrade class�е�gCriID����ʱ��������ʱ��Ҫ��HashMap�ˣ�ȡ��DetailedGrade��getScore���͵õ�����еķ�����
�����Բ����ô��ѯ��getHashMap��ֵ��
�ţ�ϣ����������㹻����ˡ�







