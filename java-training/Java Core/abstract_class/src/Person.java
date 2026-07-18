public abstract class Person {
    private String name;
    private String gender;

    public Person(String nm, String gen){
        this.name=nm;
        this.gender=gen;
    }

    //abstract method
    public abstract void work();

    public void print(){
        System.out.println("abstract class");
    };
    @Override
    public String toString(){
        return "Name="+this.name+"::Gender="+this.gender;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
