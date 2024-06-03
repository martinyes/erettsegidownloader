package com.github.martin;

import com.github.martin.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;

@AllArgsConstructor
public class InputHandler {

    private Scanner scanner;

    public InputData readData() {
        Subject subject;
        do {
            subject = Main.resolveSubject(read("Add meg a tantárgy nevét: ", false));
        } while (subject == null);

        var downloadAll = read("Hány érettségit akarsz letölteni? (mindet, egyet): ", false).equalsIgnoreCase("mindet");

        int year = -1;
        String season = null;
        if (!downloadAll) {
            year = Integer.parseInt(read("Melyik évből?: ", false));
            season = read("Melyik időszakból? (tavasz, osz): ", false);
        }

        var advancedLvl = read("Emelt szintű? (igen, nem): ", false).equalsIgnoreCase("igen");
        var answerKey = read("A megoldókulcsot(kulcsokat) is leakarod tölteni? (igen, nem): ", false).equalsIgnoreCase("igen");
        var downloadPath = read("Add meg a letöltés helyét: ", false);

        return new InputData(subject, year, season, advancedLvl, downloadAll, answerKey, downloadPath);
    }

    private String read(String msg, boolean newLine) {
        if (newLine) System.out.println(msg);
        else System.out.print(msg);
        return scanner.next();
    }
}

@AllArgsConstructor
@Getter
class InputData {

    private Subject subject;
    private int year;
    private String season;
    private boolean advancedLvl, downloadAll, answerKey;
    private String downloadPath;
}