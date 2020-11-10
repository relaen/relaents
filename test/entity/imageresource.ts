
import {DeviceAsset} from './deviceasset'
import {DeviceModel} from './devicemodel'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_image_resource",'tunnel')
export class ImageResource extends BaseEntity{
	@Id()
	@Column({
		name:'image_resource_id',
		type:'int',
		nullable:false
	})
	imageResourceId:number;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	url:string;

	@Column({
		name:'res_type',
		type:'int',
		nullable:true
	})
	resType:number;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	enabled:number;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'DeviceAsset',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'imageResource',lazyFetch:true})
	deviceAssets:Array<DeviceAsset>;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'imageResource',lazyFetch:true})
	deviceModels:Array<DeviceModel>;

}