public class LimparTela {

    public static void cls() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                new ProcessBuilder("clear")
                        .inheritIO()
                        .start()
                        .waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela.");
        }
    }
}
