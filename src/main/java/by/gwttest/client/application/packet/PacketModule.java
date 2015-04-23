package by.gwttest.client.application.packet;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PacketModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(PacketPagePresenter.class,
				PacketPagePresenter.MyView.class, PacketPageView.class,
				PacketPagePresenter.MyProxy.class);
	}
}