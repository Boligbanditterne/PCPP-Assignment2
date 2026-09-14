package exercises04;

class Person{
    private static long curr = 0;
    private long id;
    private String name;
    private int zip;
    private String address;
    

    public Person(long id){
        if(id<0){
            return;
        }
        synchronized (Person.class) {
            if(curr == 0){
                this.id = id;
                curr = id + 1;
            }
            else{
                this.id = curr;
            }
        }
        
    }
    public Person(){
        synchronized (Person.class) {
            this.id = curr;
            curr++;
        }
        this.name = "";
        this.zip = 0;
        this.address = "";
    }

    public synchronized long getId() {
        return id;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized int getZip() {
        return zip;
    }

    public synchronized String getAddress() {
        return address;
    }

    public synchronized void changeAddress(String newaddress, int newzip){
        this.address = newaddress;
        this.zip = newzip;
    }

    public synchronized void setName(String newname){
        this.name = newname;
    }



    public static void main(String[] args) {
        Person person = new Person(2101L);
        person.setName("Alice");
        person.changeAddress("Amagerbrogade", 2300);
    }
}
