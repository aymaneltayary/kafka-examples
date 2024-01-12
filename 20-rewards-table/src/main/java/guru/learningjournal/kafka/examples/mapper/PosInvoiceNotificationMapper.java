package guru.learningjournal.kafka.examples.mapper;

import guru.learningjournal.kafka.examples.model.Notification;
import guru.learningjournal.kafka.examples.model.PosInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import  guru.learningjournal.kafka.examples.AppConfigs;

@Mapper(imports = AppConfigs.class)
public interface PosInvoiceNotificationMapper {

    @Mapping(target = "earnedLoyaltyPoints",expression = "java(source.getTotalAmount() * AppConfigs.LOYALTY_FACTOR)" )
    @Mapping(target = "totalLoyaltyPoints", expression = "java(AppConfigs.INITIAL_TOTAL_LOYALTY_POINTS_ZERO)")
    Notification posInvoiceToNotification(PosInvoice source);

}
