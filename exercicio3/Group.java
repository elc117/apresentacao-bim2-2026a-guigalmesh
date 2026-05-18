import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
        this.students = new ArrayList<Student>();
        this.professors = new ArrayList<Professor>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Student s) {
        this.students.add(s);
    }

    public void addMember(Professor p) {
        this.professors.add(p);
    }

    public ArrayList<String> getContactInfos() {
        ArrayList<String> contact = new ArrayList<String>();
        for (Student s: students) {
            contact.add(s.getContactInfo());
        }
        for (Professor p: professors) {
            contact.add(p.getContactInfo());
        }
        return contact;
    }

    public boolean userExists(String userId) {
        for (Student s : students) {
            if (s.getUserId().equals(userId)) {
                return true;
            }
        }

        for (Professor p : professors) {
            if (p.getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    public int countMembers() {
        return this.students.size() + this.professors.size();
    }
}