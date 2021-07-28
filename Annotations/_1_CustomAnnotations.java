
public class _1_CustomAnnotations {
    @interface Hazard {
        /* Notice implicit modifiers below */
        int danger();                               /* Required Element */
        public String description() default "Fire"; /* Optional Element */
        public static final int UNKNOWN = -1;       /* Constant Variable */

        /* Following do NOT compile, UNCOMMENT to see why */
//        String name() default new String("");
//        String title() default null;
//        Integer height() default 2;
//        String[][] generalInfo();
//        protected int material();
//        private String friendly();
//        final boolean isBunny();
    }


    @Hazard(danger = 100, description = "Wind Damage")
    class BrokenWindow { }
}