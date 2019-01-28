/*
 * Copyright (C) 2019 Stuart Davies (stuartdd)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package parser.exception;

import parser.scanner.Scanner;

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
