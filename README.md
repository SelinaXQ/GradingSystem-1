# GradingSystem

*�г�ÿ��ҳ����Ҫ�õ��ĺ����͹���class(��Ҫ���ε���һ�����ڵ�����

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
public Course getCourseInfo(String cID)
public ArrayList<Course> getCoursesBySemester(String semester)
return ��ǰѧ�ڵ����пγ�

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
Save����ָ��������ҳ���ϵĽ���������ܴ���Delete

class����ǰҳ�棩��

Course 
ArrayList<GeneralCriteria>
ArrayList<DetailedCriteria> 
(ÿ��GeneralCriteria ��Ӧһ��ArrayList<DetailedCriteria>��
method:

public ArrayList<GeneralCriteria> getGeneralCriteriasByCourseID(String cID, boolean ifTemplate) 
public boolean saveGeneralCriterias(ArrayList<GeneralCriteria> gCris, boolean ifTemplate)
return false��ʾ�������İٷֱȲ�����1
public void deleteGeneralCriteria(GeneralCriteria gCriteria, boolean ifTemplate) 

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public void deleteDetailedCriteria(DetailedCriteria dCriteria, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public void saveCriteriaAsTemplate(ArrayList<GeneralCriteria> gCris, ArrayList<DetailedCriteria> dCris) 

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
��ҳֻ����UI��������ҳ��4ͬһ��ɾ�Ĳ�ͺ���

7. Go to Course
�����ҳ��Ӧ��������Save, һ��Save���ֱ�׼��һ��Save��֣�

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

public ArrayList<DetailedCriteria> getDetailedCriteriasByCourseID(String gCriID, boolean ifTemplate)
public boolean saveDetailedCriterias(ArrayList<DetailedCriteria> dCris, boolean ifTemplate)

public ArrayList<GiveDetailedGrades> getStudentsDetailedGrades(Course course, DetailedCriteria dCriteria��
����ֱ�Ӷ�Ӧ���ÿһ�е�ArrayList��ÿһ���ʾһ��ѧ��

public void updateStudentsDetailedGrade(DetailedCriteria dCriteria, ArrayList<GiveDetailedGrades> dGs) 
��Ӧ���ÿһ�е�ArrayList��ÿһ���ʾһ��ѧ��

8. StudentManagement

class����ǰҳ�棩��
ArrayList<StudentInfo>
Course 

method:
public ArrayList<StudentInfo> getStudentsByCourseID(Course course) 
public void saveOpUpdateStudentsInfo(ArrayList<StudentInfo> sInfos, Course c)

9. OverView




