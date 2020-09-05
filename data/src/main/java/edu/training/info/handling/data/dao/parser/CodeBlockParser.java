package edu.training.info.handling.data.dao.parser;

import edu.training.info.handling.domain.bean.CodeBlock;
import edu.training.info.handling.domain.bean.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeBlockParser extends TextParser {

    private final String OPEN_CODE_BLOCK_REGEX = "\\{";
    private final String CLOSE_CODE_BLOCK_REGEX = "\\}";

    private Pattern openCodePattern = Pattern.compile(OPEN_CODE_BLOCK_REGEX);
    private Pattern closeCodePattern = Pattern.compile(CLOSE_CODE_BLOCK_REGEX);

    @Override
    public List<? extends TextComponent> parse(String string) {
        List<CodeBlock> codeBlocks = new ArrayList<>();

        Matcher openCodeMatcher = openCodePattern.matcher(string);
        long openCount = openCodeMatcher.results().count();

        Matcher closeCodeMatcher = closeCodePattern.matcher(string);
        long closeCount = closeCodeMatcher.results().count();

        if (openCount > 0 && openCount == closeCount) {
            CodeBlock codeBlock = new CodeBlock(string);
            codeBlocks.add(codeBlock);
        } else {
            if (nextParser != null) {
                return nextParser.parse(string);
            }
        }

        return codeBlocks;
    }
}
