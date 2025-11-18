# Java Tetris

Jednoduchá desktopová hra Tetris napsaná v Javě s využitím Swing pro grafické uživatelské rozhraní. Projekt slouží jako demonstrace základních principů herního vývoje v Javě, včetně herní smyčky, vykreslování, detekce kolizí a ovládání pomocí klávesnice.

## Funkce

-   Klasická hratelnost Tetrisu.
-   Bodovací systém založený na počtu vyčištěných řádků.
-   Zobrazení dalšího dílku.
-   Zvyšující se rychlost pádu dílků s rostoucím skóre.
-   Kromě standardních tetromin (I, L, O, S, T) obsahuje i několik vlastních a netradičních tvarů pro větší variabilitu.

## Ovládání

-   **Šipka vlevo**: Posun dílku doleva
-   **Šipka vpravo**: Posun dílku doprava
-   **Šipka dolů**: Urychlení pádu dílku
-   **Šipka nahoru**: Rotace dílku

## Jak spustit projekt

Nejjednodušší způsob, jak projekt zkompilovat a spustit, je pomocí vývojového prostředí (IDE).

### Požadavky

-   Nainstalovaný **Java Development Kit (JDK), verze 17 nebo novější**.
-   Vývojové prostředí (IDE), například [IntelliJ IDEA](https://www.jetbrains.com/idea/) nebo [Eclipse](https://www.eclipse.org/).

### Spuštění v IDE (doporučený způsob)

1.  Otevři projekt ve svém IDE (např. IntelliJ IDEA).
2.  Prostředí by mělo automaticky rozpoznat strukturu projektu a závislosti.
3.  Najdi soubor `src/tetris/Main.java`. Tento soubor obsahuje hlavní metodu `public static void main(String[] args)`, která je vstupním bodem aplikace.
4.  Klikni na tento soubor pravým tlačítkem a vyber možnost **"Run 'Main.main()'"**.
5.  Hra by se měla spustit v novém okně.

### Spuštění z příkazové řádky (pro pokročilé)

Tato metoda je alternativou, pokud nechceš používat IDE.

1.  **Kompilace:**
    Otevři terminál v kořenovém adresáři projektu a zkompiluj všechny zdrojové soubory do výstupní složky (např. `out`):
    
