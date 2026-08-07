import java.util.regex.*;

public class TemplateFiller {

    public static String fillTemplate(String template, String[] names, String[] values) {

        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(template);

        StringBuilder result = new StringBuilder();
        int last = 0;

        while (m.find()) {

            result.append(template.substring(last, m.start()));

            String key = m.group(1);
            String value = "[?]";

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(key)) {
                    value = values[i];
                    break;
                }
            }

            result.append(value);
            last = m.end();
        }

        result.append(template.substring(last));

        return result.toString();
    }
}