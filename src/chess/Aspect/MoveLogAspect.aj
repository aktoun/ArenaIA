package chess.Aspect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import chess.agent.*;

public aspect MoveLogAspect 
{
	
	private static File file;
	
    // Génération du fichier
    after(): execution(void *.setupChessBoard()) {
    	initLogFile();
    }

    // Log info du mouvement
    after(Move mv): execution(void *.movePiece(Move)) && args(mv) {
    	writeText(mv.toString() + "\n");
    }

    private void initLogFile() {
        String fileName = "bin/gameMove.log";
        file = new File(fileName);
        writeText("////////////////////////////////// NEW GAME ///////////////////////////////////////\n\n");
    }

    private void writeText(String text) {
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(text);
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

