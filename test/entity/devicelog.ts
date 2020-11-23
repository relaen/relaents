import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Device} from './device'

@Entity("t_device_log",'tunnel')
export class DeviceLog extends BaseEntity{
	@Id()
	@Column({
		name:'device_log_id',
		type:'int',
		nullable:false
	})
	private deviceLogId:number;

	@ManyToOne({entity:'Device',eager:false})
	@JoinColumn({name:'devide_id',refName:'devide_id'})
	private device:Device;

	@Column({
		name:'log_time',
		type:'int',
		nullable:true
	})
	private logTime:number;

	@Column({
		name:'operator',
		type:'int',
		nullable:true
	})
	private operator:number;

	@Column({
		name:'value',
		type:'string',
		nullable:true
	})
	private value:string;

	public getDeviceLogId():number{
		return this.deviceLogId;
	}
	public setDeviceLogId(value:number){
		this.deviceLogId = value;
	}

	public getDevice():Device{
		return this.device;
	}
	public setDevice(value:Device){
		this.device = value;
	}

	public getLogTime():number{
		return this.logTime;
	}
	public setLogTime(value:number){
		this.logTime = value;
	}

	public getOperator():number{
		return this.operator;
	}
	public setOperator(value:number){
		this.operator = value;
	}

	public getValue():string{
		return this.value;
	}
	public setValue(value:string){
		this.value = value;
	}

}