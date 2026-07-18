public enum EnumOperator implements Operator{
    MUTIPLE {
        @Override
        public int calculate(int num1, int num2) {
            return num1 * num2;
        }
    },
    // Trừ 2 số.
    SUBTRACT {
        @Override
        public int calculate(int num1, int num2) {
            return num1 - num2;
        }
    };
}
