package wanghg;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by wanghg on 1/4/2017.
 */
public class UnicodeTest {

    public static Map<Byte, String> generalCategories() {
        Map<Byte, String> rv = new HashMap<Byte, String>();
        //        static byte	COMBINING_SPACING_MARK General category "Mc" in the Unicode specification.
        rv.put(Character.COMBINING_SPACING_MARK, "Mc");
        //        static byte	CONNECTOR_PUNCTUATION General category "Pc" in the Unicode specification.
        rv.put(Character.CONNECTOR_PUNCTUATION, "Pc");
        //        static byte	CONTROL General category "Cc" in the Unicode specification.
        rv.put(Character.CONTROL, "Cc");
        //        static byte	CURRENCY_SYMBOL General category "Sc" in the Unicode specification.
        rv.put(Character.CURRENCY_SYMBOL, "Sc");
        //        static byte	DASH_PUNCTUATION General category "Pd" in the Unicode specification.
        rv.put(Character.DASH_PUNCTUATION, "Pd");
        //        static byte	DECIMAL_DIGIT_NUMBER General category "Nd" in the Unicode specification.
        rv.put(Character.DECIMAL_DIGIT_NUMBER, "Nd");
        //        static byte	ENCLOSING_MARK General category "Me" in the Unicode specification.
        rv.put(Character.ENCLOSING_MARK, "Me");
        //        static byte	END_PUNCTUATION General category "Pe" in the Unicode specification.
        rv.put(Character.END_PUNCTUATION, "Pe");
        //        static byte	FINAL_QUOTE_PUNCTUATION General category "Pf" in the Unicode specification.
        rv.put(Character.FINAL_QUOTE_PUNCTUATION, "Pf");
        //        static byte	FORMAT General category "Cf" in the Unicode specification.
        rv.put(Character.FORMAT, "Cf");
        //        static byte	INITIAL_QUOTE_PUNCTUATION General category "Pi" in the Unicode specification.
        rv.put(Character.INITIAL_QUOTE_PUNCTUATION, "Pi");
        //        static byte	LETTER_NUMBER General category "Nl" in the Unicode specification.
        rv.put(Character.LETTER_NUMBER, "Nl");
        //        static byte	LINE_SEPARATOR General category "Zl" in the Unicode specification.
        rv.put(Character.LINE_SEPARATOR, "Zl");
        //        static byte	LOWERCASE_LETTER General category "Ll" in the Unicode specification.
        rv.put(Character.LOWERCASE_LETTER, "Ll");
        //        static byte	MATH_SYMBOL General category "Sm" in the Unicode specification.
        rv.put(Character.MATH_SYMBOL, "Sm");
        //        static byte	MODIFIER_LETTER General category "Lm" in the Unicode specification.
        rv.put(Character.MODIFIER_LETTER, "Lm");
        //        static byte	MODIFIER_SYMBOL General category "Sk" in the Unicode specification.
        rv.put(Character.MODIFIER_SYMBOL, "Sk");
        //        static byte	NON_SPACING_MARK General category "Mn" in the Unicode specification.
        rv.put(Character.NON_SPACING_MARK, "Mn");
        //        static byte	OTHER_LETTER General category "Lo" in the Unicode specification.
        rv.put(Character.OTHER_LETTER, "Lo");
        //        static byte	OTHER_NUMBER General category "No" in the Unicode specification.
        rv.put(Character.OTHER_NUMBER, "No");
        //        static byte	OTHER_PUNCTUATION General category "Po" in the Unicode specification.
        rv.put(Character.OTHER_PUNCTUATION, "Po");
        //        static byte	OTHER_SYMBOL General category "So" in the Unicode specification.
        rv.put(Character.OTHER_SYMBOL, "So");
        //        static byte	PARAGRAPH_SEPARATOR General category "Zp" in the Unicode specification.
        rv.put(Character.PARAGRAPH_SEPARATOR, "Zp");
        //        static byte	PRIVATE_USE General category "Co" in the Unicode specification.
        rv.put(Character.PRIVATE_USE, "Co");
        //        static byte	SPACE_SEPARATOR General category "Zs" in the Unicode specification.
        rv.put(Character.SPACE_SEPARATOR, "Zs");
        //        static byte	START_PUNCTUATION General category "Ps" in the Unicode specification.
        rv.put(Character.START_PUNCTUATION, "Ps");
        //        static byte	SURROGATE General category "Cs" in the Unicode specification.
        rv.put(Character.SURROGATE, "Cs");
        //        static byte	TITLECASE_LETTER General category "Lt" in the Unicode specification.
        rv.put(Character.TITLECASE_LETTER, "Lt");
        //        static byte	UNASSIGNED General category "Cn" in the Unicode specification.
        rv.put(Character.UNASSIGNED, "Cn");
        //        static byte	UPPERCASE_LETTER General category "Lu" in the Unicode specification.
        rv.put(Character.UPPERCASE_LETTER, "Lu");
        return rv;
    }

    @Test
    public void testCategory() {
        Map<Byte, String> map = generalCategories();
        assertEquals("Ll", map.get(Byte.valueOf((byte) Character.getType('a'))));
        assertEquals("Lu", map.get(Byte.valueOf((byte) Character.getType('A'))));
        assertEquals("Lt", map.get(Byte.valueOf((byte) Character.getType('\u1f88'))));
        System.out.println("\u1f88");
        assertEquals("Lo", map.get(Byte.valueOf((byte) Character.getType('\u01c2'))));
        System.out.println("\u01c2");
        assertEquals("Nl", map.get(Byte.valueOf((byte) Character.getType('\u2163'))));
        System.out.println("\u2163");
        // 🍎 U+1F34E
        // 🌞 U+1F31E
        // 🍷 U+1F377
        StringBuilder sb = new StringBuilder("王");
        sb.append(Character.toChars(0x1f34e));
        sb.append(Character.toChars(0x1f31e));
        sb.append(Character.toChars(0x1f377));
        String s = sb.toString();
        int n = s.codePointCount(0, s.length());
        System.out.printf("n = %d\n", n);
        for (int i = 0; i < s.length(); ) {
            int cp = s.codePointAt(i);
            char[] chars = Character.toChars(cp);
            System.out.printf("%8x: %s %8s\n", cp, String.valueOf(chars),
                    map.get(Byte.valueOf((byte) Character.getType(cp))));
            i += chars.length;
        }
    }

    @Test
    public void testRegexWithGc() {
        assertTrue("a".matches("\\p{Ll}"));
        assertFalse("_a_".matches("\\p{Ll}"));
        assertTrue("apple".matches("\\p{Ll}+"));
        assertTrue("A".matches("\\p{Lu}"));
        assertTrue("APPLE".matches("\\p{Lu}+"));
        assertTrue("Apple".matches("\\p{Lu}\\p{Ll}+"));
        assertTrue("ApPle".matches("[\\p{Lu}\\p{Ll}]+"));
        assertTrue("琅琊".matches("[\\p{Lo}]+"));
        assertTrue("🍎".matches("\\p{So}"));
        assertTrue("a🌞Z".matches("a\\p{So}Z"));
        assertTrue("🍎🌞🍷".matches("\\p{So}\\p{So}\\p{So}"));
        assertTrue("🍎本🍷末🌞".matches("[\\p{Lo}\\p{So}]+"));
        assertTrue("🍎本🍷末🌞".matches("[\\p{gc=Lo}\\p{gc=So}]{5}"));
        // ҂  U+0482
        // 🍎 U+1F34E
        // 🌞 U+1F31E
        assertTrue("🍎҂🌞".matches("\\p{So}{1,3}}"));
    }
}

