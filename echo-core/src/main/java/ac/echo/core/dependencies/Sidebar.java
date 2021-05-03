package ac.echo.core.dependencies;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

@ToString
public class Sidebar {
    @Getter @Setter List<String> items;
    @Getter private final Scoreboard scoreboard;
    @Getter private final Multimap<Integer,SpecialTeam> teams;
    @Getter private final Objective objective;

    @Getter @Setter int id;


    /**
     * Creates a new sidebar with a new scoreboard attachment.
     */
    public Sidebar(int id) {
        this(Bukkit.getScoreboardManager().getNewScoreboard(), id);
    }


    /**
     * Creates a new titled sidebar with a new scoreboard attachment.
     * @param title
     */
    public Sidebar(String title, int id) {
        this(Bukkit.getScoreboardManager().getNewScoreboard(), title, id);
    }

    /**
     * Creates a new sidebar with the given scoreboard.
     */
    public Sidebar(Scoreboard scoreboard, int id) {
        this(scoreboard, UUID.randomUUID().toString().substring(0, 6), id);
    }

    /**
     * Creates a new titles sidebar with the given scoreboard.
     * @param title
     */
    public Sidebar(Scoreboard scoreboard, String title, int id) {
        Preconditions.checkNotNull(scoreboard, "Sidebar scoreboard must not be null");
        Preconditions.checkNotNull(title, "Sidebar title must not be null");

        this.id = id;
        this.scoreboard = scoreboard;
        this.teams = ArrayListMultimap.create();

        this.objective = scoreboard.registerNewObjective(title, "dummy");
        this.objective.setDisplayName(title);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    /**
     * Retrieve the title of the sidebar.
     * @return
     */
    public String getTitle() {
        return this.objective.getDisplayName();
    }

    /**
     * Changes the title of the sidebar.
     * @param text
     */
    public void setTitle(String text) {
        this.objective.setDisplayName(text);
    }

    /**
     * Adds a line to the sidebar at the given line (score) number.
     * @param line
     * @param text
     */
    public void set(int line, String text) {
        text = fixDuplicates(text);
        SpecialTeam specialTeam = createSpecialTeam(text, line);
        Team team = specialTeam.getTeam();
        team.addEntry(specialTeam.getLineText());
        this.objective.getScore(specialTeam.getLineText()).setScore(line);
        this.teams.put(line, specialTeam);
    }

    /**
     * Get all text at the given line (score) number.
     * @param line
     * @return
     */
    public List<String> get(int line) {
        List<String> lines = new ArrayList<>();
        for (SpecialTeam team : this.teams.get(line))
            lines.add(team.getFullText());
        return lines;
    }

    /**
     * Get the line (score) number of the given text.
     * @param text
     * @throws IllegalArgumentException If the text cannot be found in the sidebar.
     * @return
     */
    public int getLine(String text) throws IllegalArgumentException {
        for (Entry<Integer, SpecialTeam> entry : this.teams.entries())
            if (entry.getValue().getFullText().equals(text))
                return entry.getKey();
        throw new IllegalArgumentException("line not found");
    }

    /**
     * Removes all entries with the given line (score) number and sets new text.
     * @param line
     * @param text
     */
    public void replace(int line, String text) {
        remove(line);
        set(line, text);
    }

    /**
     * Removes all entries with the given text.
     * @param text
     */
    public void remove(String text) {
        remove(text, null);
    }

    /**
     * Removes all entries with the given line (score) number.
     * @param line
     */
    public void remove(int line) {
        remove(null, line);
    }

    /**
     * Removes all entries in the sidebar.
     */
    public void removeAll() {
        remove(null, null);
    }

    /**
     * Removes all entries with the given text and line (score) number.
     * @param text
     */
    public void remove(String text, Integer line) {
        Iterator<Entry<Integer, SpecialTeam>> iterator = this.teams.entries().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, SpecialTeam> entry = iterator.next();
            if (text != null && !entry.getValue().getFullText().equals(text))
                continue;
            if (line != null && !entry.getKey().equals(line))
                continue;
            this.scoreboard.resetScores(entry.getValue().getLineText());
            entry.getValue().getTeam().unregister();
            iterator.remove();
        }
    }

    private String fixDuplicates(String text) {
        boolean unique = false;
        while (!unique) {
            unique = true;
            for (SpecialTeam team : this.teams.values()) {
                if (team.getFullText().equals(text)) {
                    text += ChatColor.RESET;
                    unique = false;
                }
            }
        }
        return text;
    }

    private SpecialTeam createSpecialTeam(String text, int lineNum) {
        String[] split = getSplitText(text);

        String prefix = split[0];
        String line = split[1];
        String suffix = split[2];

        Team team = this.scoreboard.registerNewTeam("sb-" + lineNum + "-" + UUID.randomUUID().toString().substring(0, 6));

        if (prefix != null)
            team.setPrefix(prefix);

        if (suffix != null)
            team.setSuffix(suffix);

        return new SpecialTeam(team, line, text);
    }

    private String[] getSplitText(String text) {
        Splitter splitter = Splitter.fixedLength(16);
        List<String> split = Lists.newArrayList(splitter.split(text).iterator());

        String prefix = null;
        String line = split.get(0);
        String suffix = null;

        if (text.length() > 16) {
            prefix = split.get(0);
            line = split.get(1);
        }
        if (text.length() > 32)
            suffix = split.get(2);

        return new String[] {prefix, line, suffix};
    }

    @ToString
    @Data
    private class SpecialTeam {
        private final Team team;
        private final String lineText;
        private final String fullText;
    }
}