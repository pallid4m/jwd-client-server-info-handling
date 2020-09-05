package edu.training.info.handling.data.dao.parser;

public final class ParserProvider {

    private static final TextParser handlerParser;

    private ParserProvider() {}

    static {
        SectionParser sectionParser = new SectionParser();
        CodeBlockParser codeBlockParser = new CodeBlockParser();
        ParagraphParser paragraphParser = new ParagraphParser();

        sectionParser.setNextParser(codeBlockParser);
        codeBlockParser.setNextParser(paragraphParser);
        handlerParser = sectionParser;
    }

    public static TextParser provide() {
        return handlerParser;
    }
}
