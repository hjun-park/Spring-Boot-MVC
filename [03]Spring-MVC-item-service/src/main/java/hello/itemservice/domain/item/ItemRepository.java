package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

	// 싱글톤으로 돌아가면서 여러 스레드가 접근하게 되는 상황
	// 01. 실제로 그냥 HashMap 쓰지 말 것  (concurrentHashmap)
	// 02. long도 마찬가지로 atomic long 사용 할 것
	private static final Map<Long, Item> store = new HashMap<>();
	private static long sequence = 0L;	// static

	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);

		return item;
	}


	public Item findById(Long id) {
		return store.get(id);
	}

	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}

	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	public void clearStore() {
		store.clear();
	}


}
