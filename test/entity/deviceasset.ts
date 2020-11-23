import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {DeviceModel} from './devicemodel'
import {ImageResource} from './imageresource'

@Entity("t_device_asset",'tunnel')
export class DeviceAsset extends BaseEntity{
	@Id()
	@Column({
		name:'device_asset_id',
		type:'int',
		nullable:false
	})
	private deviceAssetId:number;

	@ManyToOne({entity:'DeviceModel',eager:false})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	private deviceModel:DeviceModel;

	@ManyToOne({entity:'ImageResource',eager:false})
	@JoinColumn({name:'image_resource_id',refName:'image_resource_id'})
	private imageResource:ImageResource;

	public getDeviceAssetId():number{
		return this.deviceAssetId;
	}
	public setDeviceAssetId(value:number){
		this.deviceAssetId = value;
	}

	public getDeviceModel():DeviceModel{
		return this.deviceModel;
	}
	public setDeviceModel(value:DeviceModel){
		this.deviceModel = value;
	}

	public getImageResource():ImageResource{
		return this.imageResource;
	}
	public setImageResource(value:ImageResource){
		this.imageResource = value;
	}

}