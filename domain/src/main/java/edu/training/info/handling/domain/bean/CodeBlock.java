package edu.training.info.handling.domain.bean;

import java.util.Objects;

public class CodeBlock implements TextComponent {
    private String codeBlock;

    public CodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    public void setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    @Override
    public String view() {
        return codeBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeBlock codeBlock1 = (CodeBlock) o;
        return Objects.equals(codeBlock, codeBlock1.codeBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeBlock);
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "codeBlock='" + codeBlock + '\'' +
                '}';
    }
}
