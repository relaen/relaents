import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Tunnel} from './tunnel'
import {DeviceModel} from './devicemodel'
import {Device} from './device'

@Entity("t_iot_server",'tunnel')
export class IotServer extends BaseEntity{
	@Id()
	@Column({
		name:'iot_server_id',
		type:'int',
		nullable:false
	})
	private iotServerId:number;

	@ManyToOne({entity:'Tunnel',eager:false})
	@JoinColumn({name:'tunnel_id',refName:'tunnel_id'})
	private tunnel:Tunnel;

	@ManyToOne({entity:'DeviceModel',eager:false})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	private deviceModel:DeviceModel;

	@Column({
		name:'server_no',
		type:'string',
		nullable:true
	})
	private serverNo:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'iotServer',eager:false})
	private devices:Array<Device>;

	public getIotServerId():number{
		return this.iotServerId;
	}
	public setIotServerId(value:number){
		this.iotServerId = value;
	}

	public getTunnel():Tunnel{
		return this.tunnel;
	}
	public setTunnel(value:Tunnel){
		this.tunnel = value;
	}

	public getDeviceModel():DeviceModel{
		return this.deviceModel;
	}
	public setDeviceModel(value:DeviceModel){
		this.deviceModel = value;
	}

	public getServerNo():string{
		return this.serverNo;
	}
	public setServerNo(value:string){
		this.serverNo = value;
	}

	public getDevices():Array<Device>{
		return this.devices;
	}
	public setDevices(value:Array<Device>){
		this.devices = value;
	}

}