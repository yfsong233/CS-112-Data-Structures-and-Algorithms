public class Foo {
        public void m1() {
            System.out.println("poo");
        }

        public void m2() {
            this.m1();
            System.out.println("foo 1");
        }
}