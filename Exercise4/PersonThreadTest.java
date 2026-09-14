package exercises04;

class PersonThreadTest {
    public void main(String[] args) throws InterruptedException {
        Person alice = new Person(1000);
        Person bob = new Person();

        Thread t1 = new Thread(() -> {
//            try {                     // to test if it prints anything other than t2
//                Thread.sleep(500);
//            } catch (Exception e) {
//            }
            alice.setName("Alice set by t1");
            bob.setName("Bob set by t1");
        });

        Thread t2 = new Thread(() -> {
            alice.setName("Alice set by t2");
            bob.setName("Bob set by t2");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Alice's id: " + alice.getId());
        System.out.println("Bob's id: " + bob.getId());

        System.out.println("Alice's name: " + alice.getName());
        System.out.println("Bob's name: " + bob.getName());
    }
}
