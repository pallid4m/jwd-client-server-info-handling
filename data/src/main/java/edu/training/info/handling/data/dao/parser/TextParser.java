package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.Text;
import edu.training.info.handling.domain.bean.TextComponent;

import java.util.List;

public abstract class TextParser {
    protected TextParser nextParser;

    private Text text = new Text();

    public abstract List<? extends TextComponent> parse(String string);

    public void setNextParser(TextParser nextTextParser) {
        this.nextParser = nextTextParser;
    }

    public void addComponent(TextComponent component) {
        text.addComponent(component);
    }

    public Text getText() {
        return text;
    }
}
