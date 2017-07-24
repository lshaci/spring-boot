package com.lshaci.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import org.junit.Before;
import org.junit.Test;

import com.lshaci.java8.domain.HouseOrder;

public class SpiltHouseOrder {

	List<HouseOrder> houseOrders = new ArrayList<>();

	@Before
	public void before() {
		for (int i = 0; i < 10; i++) {
			HouseOrder houseOrder = new HouseOrder();

			houseOrder.setOrderNo("orderNo" + i);

			if (i < 4) {
				houseOrder.setSiteCode("siteCode1");
				houseOrder.setSiteName("siteName1");
			} else if (i < 7) {
				houseOrder.setSiteCode("siteCode2");
				houseOrder.setSiteName("siteName2");
			} else {
				houseOrder.setSiteCode("siteCode3");
				houseOrder.setSiteName("siteName3");
			}
			houseOrders.add(houseOrder);
		}
	}

	@Test
	public void testSplit() throws Exception {
		List<HouseOrder> collect = houseOrders.stream()
				.sorted((o1, o2) -> o2.getSiteCode().compareTo(o1.getSiteCode()))
				.collect(toList());
		Map<String, List<HouseOrder>> collect2 = houseOrders.stream().collect(groupingBy(HouseOrder::getSiteCode));

		System.out.println(collect);
		System.out.println(collect2);
	}

}
