package org.boes.praktikum.gameclient;

public class AsciiArt {

    // Title Text "Black Jack 21"
    public String title(){
        return "$$$$$$$\\  $$\\                     $$\\                $$$$$\\                     $$\\              $$$$$$\\    $$\\   \n" +
                "$$  __$$\\ $$ |                    $$ |               \\__$$ |                    $$ |            $$  __$$\\ $$$$ |  \n" +
                "$$ |  $$ |$$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\             $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\       \\__/  $$ |\\_$$ |  \n" +
                "$$$$$$$\\ |$$ | \\____$$\\ $$  _____|$$ | $$  |            $$ | \\____$$\\ $$  _____|$$ | $$  |       $$$$$$  |  $$ |  \n" +
                "$$  __$$\\ $$ | $$$$$$$ |$$ /      $$$$$$  /       $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  /       $$  ____/   $$ |  \n" +
                "$$ |  $$ |$$ |$$  __$$ |$$ |      $$  _$$<        $$ |  $$ |$$  __$$ |$$ |      $$  _$$<        $$ |        $$ |  \n" +
                "$$$$$$$  |$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\       \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\       $$$$$$$$\\ $$$$$$\\ \n" +
                "\\_______/ \\__| \\_______| \\_______|\\__|  \\__|       \\______/  \\_______| \\_______|\\__|  \\__|      \\________|\\______|";
    }

    // cards
    public String cards(){
        return ".------.------.------.------.\n" +
                "                   |A_  _ |A /\\  |A _   |A .   |\n" +
                "                   |( \\/ )| /  \\ | ( )  | / \\  |\n" +
                "                   | \\  / | \\  / |(_x_) |(_,_) |\n" +
                "                   |  \\/ A|  \\/ A|  Y  A|  I  A|\n" +
                "                   `------^------^------'------'";
    }

    // ASCII-Art generator for building the specific game-cards:
    public String card(String color, String value){
        String value_symbol ="";

        switch(value){
            case "A":
                value_symbol = "A";
                break;
            case "2":
                value_symbol = "2";
                break;
            case "3":
                value_symbol = "3";
                break;
            case "4":
                value_symbol = "4";
                break;
            case "5":
                value_symbol = "5";
                break;
            case "6":
                value_symbol = "6";
                break;
            case "7":
                value_symbol = "7";
                break;
            case "8":
                value_symbol = "8";
                break;
            case "9":
                value_symbol = "9";
                break;
            case "10":
                value_symbol = "10";
                break;
            case "J":
                value_symbol = "J";
                break;
            case "Q":
                value_symbol = "Q";
                break;
            case "K":
                value_symbol = "K";
                break;
        }

        switch(color){
            case "spade":
                return ".------.\n" +
                        "|" +value_symbol+ " .   |\n" +
                        "| / \\  |\n" +
                        "|(_,_) |\n" +
                        "|  I  "+value_symbol+"|\n" +
                        "'------'";
            case "heart":
                return ".------.\n" +
                        "|"+value_symbol+"_  _ |\n" +
                        "|( \\/ )|\n" +
                        "| \\  / |\n" +
                        "|  \\/ "+value_symbol+"|\n" +
                        "`------^";
            case "diamond":
                return ".------.\n" +
                        "|"+value_symbol+" /\\  |\n" +
                        "| /  \\ |\n" +
                        "| \\  / |\n" +
                        "|  \\/ "+value_symbol+"|\n" +
                        "'------'";
            case "club":
                return ".------.\n" +
                        "|"+value_symbol+" _   |\n" +
                        "| ( )  |\n" +
                        "|(_x_) |\n" +
                        "|  Y  "+value_symbol+"|\n" +
                        "'------'";
        }
        return "error";
    }

    // round 1
    public String round1(){
        return "\n" +
                "  _    _ _ _                   _____ _                  _ \n" +
                " | |  | (_) |                 / ____| |                | |\n" +
                " | |__| |_| |_    ___  _ __  | (___ | |_ __ _ _ __   __| |\n" +
                " |  __  | | __|  / _ \\| '__|  \\___ \\| __/ _` | '_ \\ / _` |\n" +
                " | |  | | | |_  | (_) | |     ____) | || (_| | | | | (_| |\n" +
                " |_|  |_|_|\\__|  \\___/|_|    |_____/ \\__\\__,_|_| |_|\\__,_|\n" +
                "                                                          \n" +
                "                                                          \n";
    }

    public String end(){
        return "\n" +
                "  ______ _             _   _    _                 _     \n" +
                " |  ____(_)           | | | |  | |               | |    \n" +
                " | |__   _ _ __   __ _| | | |__| | __ _ _ __   __| |___ \n" +
                " |  __| | | '_ \\ / _` | | |  __  |/ _` | '_ \\ / _` / __|\n" +
                " | |    | | | | | (_| | | | |  | | (_| | | | | (_| \\__ \\\n" +
                " |_|    |_|_| |_|\\__,_|_| |_|  |_|\\__,_|_| |_|\\__,_|___/\n" +
                "                                                        \n" +
                "                                                        \n";
    }
}
