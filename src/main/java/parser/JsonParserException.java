/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author stuart
 */
public class JsonParserException extends RuntimeException {

    private Scanner scanner = null;

    public JsonParserException(String message, Scanner sc) {
        super(message);
        this.scanner = sc;
    }

    public JsonParserException(String message, Scanner sc, Throwable cause) {
        super(message, cause);
        this.scanner = sc;
    }

    public void setScanner(Scanner scanner) {
        if (this.scanner == null) {
            this.scanner = scanner;
        }
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage() + (scanner == null ? "" : " Parser:" + scanner.toString());
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (scanner == null ? "" : " Parser:" + scanner.toString());
    }

}
