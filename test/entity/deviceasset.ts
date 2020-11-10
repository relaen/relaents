
import {DeviceModel} from './devicemodel'
import {ImageResource} from './imageresource'
import { Entity, ManyToOne, JoinColumn, Column } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_device_asset",'tunnel')
export class DeviceAsset extends BaseEntity{
	@ManyToOne({entity:DeviceAsset,lazyFetch:true})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	deviceModel:DeviceModel;

	@ManyToOne({entity:DeviceAsset,lazyFetch:true})
	@JoinColumn({name:'image_resource_id',refName:'image_resource_id'})
	imageResource:ImageResource;

	@Column({
		name:'device_asset_id',
		type:'int',
		nullable:true
	})
	deviceAssetId:number;

}