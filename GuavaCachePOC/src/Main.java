
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Main {

	private static final int trezentosMil = 300000;
	private static final int cemMil = 30000000;

	private void runCompare() {
		List<String> searches = new ArrayList<>();
		Map<String, String> mapTest = new HashMap<>();

		Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build(); // look Ma, no CacheLoader
		String keyConst = "KeyName";
		String valueConst = "Val";

		String k;
		String v;
		int num;
		Map<String, String> list = getSeeds(keyConst, valueConst);
		mapTest.putAll(list);
		cache.putAll(list);

		searches = createSearchLists(keyConst);

		searchInMap(searches, mapTest);

		searchInGuavaCache(searches, cache);

	}

	private void searchInGuavaCache(List<String> searches, Cache<String, String> cache) {
		String k;
		String v;
		System.out.println("Pesquisando no guava cache as " + Instant.now());
		for (Iterator iterator = searches.iterator(); iterator.hasNext();) {
			k = (String) iterator.next();
			v = cache.getIfPresent(k);
		}
		System.out.println("Finalizou as " + Instant.now());
	}

	private void searchInMap(List<String> searches, Map<String, String> mapTest) {
		String k;
		String v;
		System.out.println("Pesquisando no map as " + Instant.now());
		for (Iterator iterator = searches.iterator(); iterator.hasNext();) {
			k = (String) iterator.next();
			v = mapTest.get(k);
		}
		System.out.println("Finalizou as " + Instant.now());
	}

	private List<String> createSearchLists(String keyConst) {
		Random r = new Random();
		System.out.println("criando lista de pesquisas  as " + Instant.now());
		List<String> result = new ArrayList<>();
		for (int i = 0; i < cemMil; i++) {
			result.add(keyConst + r.nextInt(cemMil));
		}
		System.out.println("Finalizou as " + Instant.now());
		return result;
	}

	private Map<String, String> getSeeds(String keyConst, String valueConst) {
		String k;
		String v;
		int num;
		Random r = new Random();
		Map<String, String> result = new HashMap<>();
		System.out.println("iniciando sementes as " + Instant.now());
		for (int i = 0; i < trezentosMil; i++) {
			num = r.nextInt(trezentosMil);
			k = keyConst + num;
			v = valueConst + num;
			result.put(k, v);
		}
		System.out.println("Finalizou as " + Instant.now());
		return result;
	};

	public static void main(String[] args) {
		Main main = new Main();
		main.runCompare();
	}

}
