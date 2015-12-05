package kiproff.lab10;

/**
 * Created by Andrew on 11/17/2015.
 */
public class fpsFinder {
    private String gameTitle;
    private String gameUrl;



    private void setGameInfo(String gameGenre){
        switch (gameGenre){
            case "Action":
            case "Veprim":
            case "Eylem":
            case "Açao":
                gameTitle="Battlefield 4";
                gameUrl="http://www.battlefield.com/battlefield-4";
                break;
            case "Adventure":
            case "Aventurë":
            case "Macera":
            case "Aventura":
                gameTitle="Fallout 4";
                gameUrl="https://www.fallout4.com";
                break;
            case "History":
            case "Histori":
            case "Geçmiş":
            case "História":
                gameTitle="Black Ops III";
                gameUrl="https://www.callofduty.com/blackops3";
                break;
            case "Terror":
            case "Terör":
                gameTitle="F.3.A.R.";
                gameUrl="https://en.wikipedia.org/wiki/F.E.A.R._3";
                break;
            case "Mystery":
            case "Mister":
            case "Gizem":
            case "Mistério":
                gameTitle="Alan Wake";
                gameUrl="http://www.alanwake.com/";
                break;
            case "Sci-Fi":
            case "Fantashkencë":
            case "Bilim-Kurgu":
            case "Ficção-Científica":
                gameTitle="Halo 5";
                gameUrl="https://www.halowaypoint.com/en-us/games/halo-5-guardians";
                break;
            default:
                gameTitle="\u2193";
                gameUrl="http://www.vg247.com/2014/08/06/best-fps-ever/";
        }
    }

    public void setGameGenre(String gameGenre){
        setGameInfo(gameGenre);
    }

    public void setGameUrl(String gameGenre){
        setGameInfo(gameGenre);
    }

    public String getGameGenre(){
        return gameTitle;
    }

    public String getGameUrl(){
        return gameUrl;
    }




}
