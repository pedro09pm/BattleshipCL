package BattleshipCL.Game;

import BattleshipCL.Game.Primitive.Ship;

public final class Cons {

	public static final String MENU_TEXT = "\n" +
			"          ┌┐ ┌─┐┌┬┐┌┬┐┬  ┌─┐┌─┐┬ ┬┬┌─┐          \n" +
			"     ───  ├┴┐├─┤ │  │ │  ├┤ └─┐├─┤│├─┘  ───     \n" +
			"────────  └─┘┴ ┴ ┴  ┴ ┴─┘└─┘└─┘┴ ┴┴┴    ────────\n" +
			"           COMMAND-LINE EDITION V.0\n \n \n           Press ENTER to continue:";
	public static final String VICTORY_TEXT = "\n" +
			"__      ___      _                   _         \n" +
			"\\ \\    / (_)    | |                 | |      \n" +
			" \\ \\  / / _  ___| |_ ___  _ __ _   _| |      \n" +
			"  \\ \\/ / | |/ __| __/ _ \\| '__| | | | |     \n" +
			"   \\  /  | | (__| || (_) | |  | |_| |_|       \n" +
			"    \\/   |_|\\___|\\__\\___/|_|   \\__, (_)   \n" +
			"                                __/ |          \n" +
			"                               |___/           \n" +
			"\n        ";
	public static final String DEFEAT_TEXT = "\n" +
			" _____  ______ ______ ______       _______         \n" +
			"|  __ \\|  ____|  ____|  ____|   /\\|__   __|      \n" +
			"| |  | | |__  | |__  | |__     /  \\  | |          \n" +
			"| |  | |  __| |  __| |  __|   / /\\ \\ | |         \n" +
			"| |__| | |____| |    | |____ / ____ \\| |_ _ _     \n" +
			"|_____/|______|_|    |______/_/    \\_\\_(_|_|_)   \n" +
			"\n         ";
	public static final String GOODBYE_TEXT = "\n" +
			" _______ _                 _                                   \n" +
			"|__   __| |               | |                                  \n" +
			"   | |  | |__   __ _ _ __ | | __  _   _  ___  _   _            \n" +
			"   | |  | '_ \\ / _` | '_ \\| |/ / | | | |/ _ \\| | | |        \n" +
			"   | |  | | | | (_| | | | |   <  | |_| | (_) | |_| |           \n" +
			"   |_|  |_| |_|\\__,_|_| |_|_|\\_\\  \\__, |\\___/ \\__,_|     \n" +
			" ______           _____  _         __/ |             _         \n" +
			"|  ____|         |  __ \\| |       |___(_)           | |       \n" +
			"| |__ ___  _ __  | |__) | | __ _ _   _ _ _ __   __ _| |        \n" +
			"|  __/ _ \\| '__| |  ___/| |/ _` | | | | | '_ \\ / _` | |      \n" +
			"| | | (_) | |    | |    | | (_| | |_| | | | | | (_| |_|        \n" +
			"|_|  \\___/|_|    |_|    |_|\\__,_|\\__, |_|_| |_|\\__, (_)    \n" +
			"                                  __/ |         __/ |          \n" +
			"                                 |___/         |___/           \n" +
			"\n                Press ENTER to quit:";

	public static final String NO_COLOR_DEFAULT = "";

	public static final int CLASSIC_MODE_BOARD_SIZE = 8;
	public static final int CLASSIC_MODE_SHOT_COUNT = 30;
	public static final int CPU_MODE_BOARD_SIZE = 10;

	public static final Ship[] CLASSIC_MODE_FLEET = new Ship[] {
			new Ship(1, 1),
			/*new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),*/
			new Ship(1, 1),
			new Ship(1, 1)
	};
	public static final Ship[] CPU_MODE_FLEET = new Ship[] {
			new Ship(1, 4),
			new Ship(1, 4),
			new Ship(1, 3),
			new Ship(1, 3),
			new Ship(1, 2),
			new Ship(1, 2),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1),
			new Ship(1, 1)
	};

}
