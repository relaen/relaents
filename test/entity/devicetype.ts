import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {DeviceModel} from './devicemodel'

@Entity("t_device_type",'tunnel')
export class DeviceType extends BaseEntity{
	@Id()
	@Column({
		name:'device_type_id',
		type:'int',
		nullable:false
	})
	private deviceTypeId:number;

	@Column({
		name:'device_type_name',
		type:'string',
		nullable:true
	})
	private deviceTypeName:string;

	@Column({
		name:'extra_fields',
		type:'string',
		nullable:true
	})
	private extraFields:string;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceType',eager:false})
	private deviceModels:Array<DeviceModel>;

	public getDeviceTypeId():number{
		return this.deviceTypeId;
	}
	public setDeviceTypeId(value:number){
		this.deviceTypeId = value;
	}

	public getDeviceTypeName():string{
		return this.deviceTypeName;
	}
	public setDeviceTypeName(value:string){
		this.deviceTypeName = value;
	}

	public getExtraFields():string{
		return this.extraFields;
	}
	public setExtraFields(value:string){
		this.extraFields = value;
	}

	public getDeviceModels():Array<DeviceModel>{
		return this.deviceModels;
	}
	public setDeviceModels(value:Array<DeviceModel>){
		this.deviceModels = value;
	}

}