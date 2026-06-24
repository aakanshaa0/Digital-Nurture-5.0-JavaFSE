public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public boolean isPositive(int num) {
        return num > 0;
    }
    public String greet(String name) {
        return name == null ? null : "Hello " + name;
    }
}