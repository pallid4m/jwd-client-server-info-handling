package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.TextComponent;

import java.util.List;

public class BufferedParser extends TextParser {
    private StringBuffer buffer = new StringBuffer();

    @Override
    public List<? extends TextComponent> parse(String string) {

        if (string.isBlank()) {
            return null;
        }

//        string = string.strip();

        if (buffer.length() > 0) {
            string = buffer.append(string).toString();
            buffer.delete(0, buffer.length());
        }

        List<? extends TextComponent> components = nextParser.parse(string);

        if (components.size() > 0) {
            for (TextComponent component : components) {
                addComponent(component);
            }
            buffer.delete(0, buffer.length());
        } else {
            buffer.append(string).append("\n");
        }

        return components;
    }
}
