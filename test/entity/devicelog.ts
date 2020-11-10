
import {Device} from './device'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_device_log",'tunnel')
export class DeviceLog extends BaseEntity{
	@Id()
	@Column({
		name:'device_log_id',
		type:'int',
		nullable:false
	})
	deviceLogId:number;

	@ManyToOne({entity:DeviceLog,lazyFetch:true})
	@JoinColumn({name:'devide_id',refName:'devide_id'})
	device:Device;

	@Column({
		name:'log_time',
		type:'int',
		nullable:true
	})
	logTime:number;

	@Column({
		name:'operator',
		type:'int',
		nullable:true
	})
	operator:number;

	@Column({
		name:'value',
		type:'string',
		nullable:true
	})
	value:string;

}