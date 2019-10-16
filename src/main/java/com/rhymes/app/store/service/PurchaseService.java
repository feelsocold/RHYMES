package com.rhymes.app.store.service;

import java.util.List;

import com.rhymes.app.store.model.BasketDto;
import com.rhymes.app.store.model.BasketListDto;
import com.rhymes.app.store.model.ProductDto;
import com.rhymes.app.store.model.RestockNotifyDto;
import com.rhymes.app.store.model.StockDto;
import com.rhymes.app.store.model.WishlistDto;

public interface PurchaseService {

	public ProductDto getProductDetail(int p_seq) throws Exception;
	public List<StockDto> getSizeList(int p_seq) throws Exception;
	public boolean insertBasket(BasketDto basket) throws Exception;
	public boolean chkBasket(BasketDto basket) throws Exception;
	public List<BasketListDto> getBasketList(String id) throws Exception;
	public void deleteBasket(int b_seq) throws Exception;
	public int updateBaksetQ(BasketDto basket) throws Exception;
	public int deleteBasektAll(BasketDto basket) throws Exception;
	public boolean insertWishlist(WishlistDto wish) throws Exception;
	public boolean chkWishlist(WishlistDto wish) throws Exception;
	public boolean deleteWishlist(WishlistDto wish) throws Exception;
	public boolean insertRestockN(RestockNotifyDto restock) throws Exception;
	public boolean chkRestockN(RestockNotifyDto restock) throws Exception;
}