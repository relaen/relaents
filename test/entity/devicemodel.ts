import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Provider} from './provider'
import {DeviceType} from './devicetype'
import {ImageResource} from './imageresource'
import {Device} from './device'
import {DeviceAsset} from './deviceasset'
import {DeviceChannel} from './devicechannel'
import {IotServer} from './iotserver'

@Entity("t_device_model",'tunnel')
export class DeviceModel extends BaseEntity{
	@Id()
	@Column({
		name:'device_model_id',
		type:'int',
		nullable:false
	})
	private deviceModelId:number;

	@ManyToOne({entity:'Provider',eager:false})
	@JoinColumn({name:'provider_id',refName:'provider_id'})
	private provider:Provider;

	@ManyToOne({entity:'DeviceType',eager:false})
	@JoinColumn({name:'device_type_id',refName:'device_type_id'})
	private deviceType:DeviceType;

	@ManyToOne({entity:'ImageResource',eager:false})
	@JoinColumn({name:'thumb_id',refName:'image_resource_id'})
	private imageResource:ImageResource;

	@Column({
		name:'product_name',
		type:'string',
		nullable:true
	})
	private productName:string;

	@Column({
		name:'model_name',
		type:'string',
		nullable:true
	})
	private modelName:string;

	@Column({
		name:'brand_name',
		type:'string',
		nullable:true
	})
	private brandName:string;

	@Column({
		name:'length',
		type:'double',
		nullable:true
	})
	private length:number;

	@Column({
		name:'width',
		type:'double',
		nullable:true
	})
	private width:number;

	@Column({
		name:'height',
		type:'double',
		nullable:true
	})
	private height:number;

	@Column({
		name:'material',
		type:'string',
		nullable:true
	})
	private material:string;

	@Column({
		name:'weight',
		type:'string',
		nullable:true
	})
	private weight:string;

	@Column({
		name:'channel_num',
		type:'int',
		nullable:true
	})
	private channelNum:number;

	@Column({
		name:'current',
		type:'double',
		nullable:true
	})
	private current:number;

	@Column({
		name:'voltage',
		type:'double',
		nullable:true
	})
	private voltage:number;

	@Column({
		name:'input_power',
		type:'double',
		nullable:true
	})
	private inputPower:number;

	@Column({
		name:'output_power',
		type:'double',
		nullable:true
	})
	private outputPower:number;

	@Column({
		name:'service_life',
		type:'double',
		nullable:true
	})
	private serviceLife:number;

	@Column({
		name:'handle_class',
		type:'string',
		nullable:true
	})
	private handleClass:string;

	@Column({
		name:'min_humidity',
		type:'double',
		nullable:true
	})
	private minHumidity:number;

	@Column({
		name:'max_humidity',
		type:'double',
		nullable:true
	})
	private maxHumidity:number;

	@Column({
		name:'min_temperature',
		type:'double',
		nullable:true
	})
	private minTemperature:number;

	@Column({
		name:'max_temperature',
		type:'double',
		nullable:true
	})
	private maxTemperature:number;

	@Column({
		name:'extra_info',
		type:'string',
		nullable:true
	})
	private extraInfo:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',eager:false})
	private devices:Array<Device>;

	@OneToMany({entity:'DeviceAsset',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',eager:false})
	private deviceAssets:Array<DeviceAsset>;

	@OneToMany({entity:'DeviceChannel',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',eager:false})
	private deviceChannels:Array<DeviceChannel>;

	@OneToMany({entity:'IotServer',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceModel',eager:false})
	private iotServers:Array<IotServer>;

	public getDeviceModelId():number{
		return this.deviceModelId;
	}
	public setDeviceModelId(value:number){
		this.deviceModelId = value;
	}

	public getProvider():Provider{
		return this.provider;
	}
	public setProvider(value:Provider){
		this.provider = value;
	}

	public getDeviceType():DeviceType{
		return this.deviceType;
	}
	public setDeviceType(value:DeviceType){
		this.deviceType = value;
	}

	public getImageResource():ImageResource{
		return this.imageResource;
	}
	public setImageResource(value:ImageResource){
		this.imageResource = value;
	}

	public getProductName():string{
		return this.productName;
	}
	public setProductName(value:string){
		this.productName = value;
	}

	public getModelName():string{
		return this.modelName;
	}
	public setModelName(value:string){
		this.modelName = value;
	}

	public getBrandName():string{
		return this.brandName;
	}
	public setBrandName(value:string){
		this.brandName = value;
	}

	public getLength():number{
		return this.length;
	}
	public setLength(value:number){
		this.length = value;
	}

	public getWidth():number{
		return this.width;
	}
	public setWidth(value:number){
		this.width = value;
	}

	public getHeight():number{
		return this.height;
	}
	public setHeight(value:number){
		this.height = value;
	}

	public getMaterial():string{
		return this.material;
	}
	public setMaterial(value:string){
		this.material = value;
	}

	public getWeight():string{
		return this.weight;
	}
	public setWeight(value:string){
		this.weight = value;
	}

	public getChannelNum():number{
		return this.channelNum;
	}
	public setChannelNum(value:number){
		this.channelNum = value;
	}

	public getCurrent():number{
		return this.current;
	}
	public setCurrent(value:number){
		this.current = value;
	}

	public getVoltage():number{
		return this.voltage;
	}
	public setVoltage(value:number){
		this.voltage = value;
	}

	public getInputPower():number{
		return this.inputPower;
	}
	public setInputPower(value:number){
		this.inputPower = value;
	}

	public getOutputPower():number{
		return this.outputPower;
	}
	public setOutputPower(value:number){
		this.outputPower = value;
	}

	public getServiceLife():number{
		return this.serviceLife;
	}
	public setServiceLife(value:number){
		this.serviceLife = value;
	}

	public getHandleClass():string{
		return this.handleClass;
	}
	public setHandleClass(value:string){
		this.handleClass = value;
	}

	public getMinHumidity():number{
		return this.minHumidity;
	}
	public setMinHumidity(value:number){
		this.minHumidity = value;
	}

	public getMaxHumidity():number{
		return this.maxHumidity;
	}
	public setMaxHumidity(value:number){
		this.maxHumidity = value;
	}

	public getMinTemperature():number{
		return this.minTemperature;
	}
	public setMinTemperature(value:number){
		this.minTemperature = value;
	}

	public getMaxTemperature():number{
		return this.maxTemperature;
	}
	public setMaxTemperature(value:number){
		this.maxTemperature = value;
	}

	public getExtraInfo():string{
		return this.extraInfo;
	}
	public setExtraInfo(value:string){
		this.extraInfo = value;
	}

	public getDevices():Array<Device>{
		return this.devices;
	}
	public setDevices(value:Array<Device>){
		this.devices = value;
	}

	public getDeviceAssets():Array<DeviceAsset>{
		return this.deviceAssets;
	}
	public setDeviceAssets(value:Array<DeviceAsset>){
		this.deviceAssets = value;
	}

	public getDeviceChannels():Array<DeviceChannel>{
		return this.deviceChannels;
	}
	public setDeviceChannels(value:Array<DeviceChannel>){
		this.deviceChannels = value;
	}

	public getIotServers():Array<IotServer>{
		return this.iotServers;
	}
	public setIotServers(value:Array<IotServer>){
		this.iotServers = value;
	}

}