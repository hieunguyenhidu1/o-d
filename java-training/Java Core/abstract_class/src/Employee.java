public class Employee extends Person{
    private int empId;

    public Employee(String nm, String gen, int id) {
        super(nm, gen);
        this.empId=id;
    }

    @Override
    public void work() {
        if(empId == 0){
            System.out.println("Not working");
        }else{
            System.out.println("Working as employee!!");
        }
    }

    @Override
    public void print(){
        System.out.println("normal class");
    }

    public static void main(String args[]){
        //coding in terms of abstract classes
//        Person hello = new Person("hello", "male");
        Person student = new Employee("Que","Female",0);
        Person employee = new Employee("Hieu","Male",123);
        student.work();
        employee.work();
        employee.print();
        //using method implemented in abstract class - inheritance
        employee.changeName("Hieu 3");
        System.out.println(employee.toString());
    }

}
