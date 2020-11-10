
import {Road} from './road'
import {TunnelType} from './tunneltype'
import {Agent} from './agent'
import {DeviceLoc} from './deviceloc'
import {IotServer} from './iotserver'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_tunnel",'tunnel')
export class Tunnel extends BaseEntity{
	@Id()
	@Column({
		name:'tunnel_id',
		type:'int',
		nullable:false
	})
	tunnelId:number;

	@ManyToOne({entity:Tunnel,lazyFetch:true})
	@JoinColumn({name:'road_id',refName:'road_id'})
	road:Road;

	@ManyToOne({entity:Tunnel,lazyFetch:true})
	@JoinColumn({name:'tunnel_type_id',refName:'tunnel_type_id'})
	tunnelType:TunnelType;

	@ManyToOne({entity:Tunnel,lazyFetch:true})
	@JoinColumn({name:'agent_id',refName:'agent_id'})
	agent:Agent;

	@Column({
		name:'turnal_no',
		type:'string',
		nullable:true
	})
	turnalNo:string;

	@Column({
		name:'longitude',
		type:'double',
		nullable:true
	})
	longitude:number;

	@Column({
		name:'latitude',
		type:'double',
		nullable:true
	})
	latitude:number;

	@Column({
		name:'length',
		type:'double',
		nullable:true
	})
	length:number;

	@Column({
		name:'pure_width',
		type:'double',
		nullable:true
	})
	pureWidth:number;

	@Column({
		name:'road_width',
		type:'double',
		nullable:true
	})
	roadWidth:number;

	@Column({
		name:'pure_height',
		type:'double',
		nullable:true
	})
	pureHeight:number;

	@Column({
		name:'start_time',
		type:'int',
		nullable:true
	})
	startTime:number;

	@Column({
		name:'lane_num',
		type:'int',
		nullable:true
	})
	laneNum:number;

	@Column({
		name:'loc_desc',
		type:'string',
		nullable:true
	})
	locDesc:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'DeviceLoc',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnel',lazyFetch:true})
	deviceLocs:Array<DeviceLoc>;

	@OneToMany({entity:'IotServer',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'tunnel',lazyFetch:true})
	iotServers:Array<IotServer>;

}