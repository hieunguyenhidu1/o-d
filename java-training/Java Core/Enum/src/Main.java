public class Main {
    public static void main(String[] args) {

        int a = 10, b = 5;

        // Gọi phép nhân
        Operator multiply = EnumOperator.MUTIPLE;
        System.out.println("Multiply: " + multiply.calculate(a, b)); // 50

        // Gọi phép trừ
        Operator subtract = EnumOperator.SUBTRACT;
        System.out.println("Subtract: " + subtract.calculate(a, b)); // 5
        }
}