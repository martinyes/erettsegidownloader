package com.github.martin;

import com.github.martin.subject.Subject;
import com.github.martin.subject.impl.History;
import com.github.martin.subject.impl.Hungarian;
import com.github.martin.subject.impl.Math;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final List<Subject> subjects;

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            new Main().run(scanner);
        }
    }

    private void run(Scanner scanner) {
        var inputHandler = new InputHandler(scanner);

        System.err.println("FONTOS! 2013 előtti érettségiket nem tölt le a program " +
                "(Mert a fos oktatás.hu 6 fajta URL szintaxist használ)!");
        System.out.println("Jelenlegi könyvtár: " + System.getProperty("user.dir"));
        System.out.printf("Jelenleg elérhető tantárgyak (%d):\n", subjects.size());
        System.out.println(subjects.stream()
                .map(Subject::displayName)
                .collect(Collectors.joining(", ")));

        var result = inputHandler.readData();
        var downloader = new Downloader();

        if (result.isDownloadAll()) downloader.downloadAll(result);
        else downloader.downloadSingle(result);
    }

    public static Subject resolveSubject(String name) {
        return subjects.stream().filter(s -> s.resolve(name)).findFirst().orElse(null);
    }

    static {
        subjects = Arrays.asList(
                new Math(), new History(), new Hungarian()
        );
    }
}