
import {Provider} from './provider'
import {DeviceType} from './devicetype'
import {ImageResource} from './imageresource'
import {Device} from './device'
import {DeviceAsset} from './deviceasset'
import {DeviceChannel} from './devicechannel'
import {IotServer} from './iotserver'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_device_model",'tunnel')
export class DeviceModel extends BaseEntity{
	@Id()
	@Column({
		name:'device_model_id',
		type:'int',
		nullable:false
	})
	deviceModelId:number;

	@ManyToOne({entity:DeviceModel,lazyFetch:true})
	@JoinColumn({name:'provider_id',refName:'provider_id'})
	provider:Provider;

	@ManyToOne({entity:DeviceModel,lazyFetch:true})
	@JoinColumn({name:'device_type_id',refName:'device_type_id'})
	deviceType:DeviceType;

	@ManyToOne({entity:DeviceModel,lazyFetch:true})
	@JoinColumn({name:'thumb_id',refName:'thumb_id'})
	imageResource:ImageResource;

	@Column({
		name:'product_name',
		type:'string',
		nullable:true
	})
	productName:string;

	@Column({
		name:'model_name',
		type:'string',
		nullable:true
	})
	modelName:string;

	@Column({
		name:'brand_name',
		type:'string',
		nullable:true
	})
	brandName:string;

	@Column({
		name:'length',
		type:'double',
		nullable:true
	})
	length:number;

	@Column({
		name:'width',
		type:'double',
		nullable:true
	})
	width:number;

	@Column({
		name:'height',
		type:'double',
		nullable:true
	})
	height:number;

	@Column({
		name:'material',
		type:'string',
		nullable:true
	})
	material:string;

	@Column({
		name:'weight',
		type:'string',
		nullable:true
	})
	weight:string;

	@Column({
		name:'channel_num',
		type:'int',
		nullable:true
	})
	channelNum:number;

	@Column({
		name:'current',
		type:'double',
		nullable:true
	})
	current:number;

	@Column({
		name:'voltage',
		type:'double',
		nullable:true
	})
	voltage:number;

	@Column({
		name:'input_power',
		type:'double',
		nullable:true
	})
	inputPower:number;

	@Column({
		name:'output_power',
		type:'double',
		nullable:true
	})
	outputPower:number;

	@Column({
		name:'service_life',
		type:'double',
		nullable:true
	})
	serviceLife:number;

	@Column({
		name:'handle_class',
		type:'string',
		nullable:true
	})
	handleClass:string;

	@Column({
		name:'min_humidity',
		type:'double',
		nullable:true
	})
	minHumidity:number;

	@Column({
		name:'max_humidity',
		type:'double',
		nullable:true
	})
	maxHumidity:number;

	@Column({
		name:'min_temperature',
		type:'double',
		nullable:true
	})
	minTemperature:number;

	@Column({
		name:'max_temperature',
		type:'double',
		nullable:true
	})
	maxTemperature:number;

	@Column({
		name:'extra_info',
		type:'string',
		nullable:true
	})
	extraInfo:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',lazyFetch:true})
	devices:Array<Device>;

	@OneToMany({entity:'DeviceAsset',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',lazyFetch:true})
	deviceAssets:Array<DeviceAsset>;

	@OneToMany({entity:'DeviceChannel',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',lazyFetch:true})
	deviceChannels:Array<DeviceChannel>;

	@OneToMany({entity:'IotServer',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',lazyFetch:true})
	iotServers:Array<IotServer>;

}