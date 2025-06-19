package simulationtry;

import java.util.HashMap;
import java.util.Map;

import simulationtry.creature.Herbivore;
import simulationtry.creature.Predator;
import simulationtry.entities.Entity;
import simulationtry.entities.Grass;
import simulationtry.entities.Rock;
import simulationtry.entities.Tree;

public class EntityRenderer {
	private final Map<Class<? extends Entity>, String> entitySymbols = new HashMap<>();

	public EntityRenderer() {
		// Добавляем отображение для каждого типа сущности
		entitySymbols.put(Herbivore.class, "\uD83D\uDC0F"); // 🐏 овца (Herbivore)
		entitySymbols.put(Predator.class, "\uD83D\uDC3A"); // 🐺 волк (Predator)
		entitySymbols.put(Grass.class, "\uD83C\uDF3F"); // 🌿 трава (Grass)
		entitySymbols.put(Tree.class, "\uD83C\uDF33"); // 🌳 дерево (Tree)
		entitySymbols.put(Rock.class, "\u26CF\uFE0F"); // ⛏️ (Rock)
		// Добавляй другие сущности по необходимости
	}

	public String getSymbol(Entity entity) {
		if (entity == null)
			return " "; // для пустых клеток

		String symbol = entitySymbols.get(entity.getClass());
		return symbol != null ? symbol : "?"; // если нет символа — "?"
	}
}