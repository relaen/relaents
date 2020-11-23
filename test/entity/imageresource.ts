import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {DeviceAsset} from './deviceasset'
import {DeviceModel} from './devicemodel'

@Entity("t_image_resource",'tunnel')
export class ImageResource extends BaseEntity{
	@Id()
	@Column({
		name:'image_resource_id',
		type:'int',
		nullable:false
	})
	private imageResourceId:number;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	private url:string;

	@Column({
		name:'res_type',
		type:'int',
		nullable:true
	})
	private resType:number;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	private enabled:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'DeviceAsset',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'imageResource',eager:false})
	private deviceAssets:Array<DeviceAsset>;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'imageResource',eager:false})
	private deviceModels:Array<DeviceModel>;

	public getImageResourceId():number{
		return this.imageResourceId;
	}
	public setImageResourceId(value:number){
		this.imageResourceId = value;
	}

	public getUrl():string{
		return this.url;
	}
	public setUrl(value:string){
		this.url = value;
	}

	public getResType():number{
		return this.resType;
	}
	public setResType(value:number){
		this.resType = value;
	}

	public getEnabled():number{
		return this.enabled;
	}
	public setEnabled(value:number){
		this.enabled = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getDeviceAssets():Array<DeviceAsset>{
		return this.deviceAssets;
	}
	public setDeviceAssets(value:Array<DeviceAsset>){
		this.deviceAssets = value;
	}

	public getDeviceModels():Array<DeviceModel>{
		return this.deviceModels;
	}
	public setDeviceModels(value:Array<DeviceModel>){
		this.deviceModels = value;
	}

}